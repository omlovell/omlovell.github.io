// BACKEND FOR ENHANCEMENT THREE

require("dotenv").config();

const express = require("express");
const cors = require("cors");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const { MongoClient, ObjectId } = require("mongodb");

const app = express();
app.use(cors());
app.use(express.json());

const PORT = process.env.PORT || 3000;
const JWT_SECRET = process.env.JWT_SECRET;
const DB_NAME = process.env.DB_NAME || "weightapp";

if (!process.env.MONGODB_URI) throw new Error("Missing MONGODB_URI in .env");
if (!JWT_SECRET) throw new Error("Missing JWT_SECRET in .env");

const client = new MongoClient(process.env.MONGODB_URI);

let users;
let weights;

// --- Auth middleware (protects /weights routes)
function auth(req, res, next) {
  const header = req.headers.authorization;
  if (!header) return res.sendStatus(401);

  const parts = header.split(" ");
  if (parts.length !== 2 || parts[0] !== "Bearer") return res.sendStatus(401);

  const token = parts[1];

  try {
    const payload = jwt.verify(token, JWT_SECRET);
    req.userId = payload.userId;
    next();
  } catch {
    return res.sendStatus(401);
  }
}

// --- Health check (useful for testing)
app.get("/", (req, res) => res.json({ ok: true }));

// --- Register
app.post("/auth/register", async (req, res) => {
  const { email, password } = req.body || {};
  if (!email || !password) return res.status(400).json({ error: "Missing email or password" });

  const existing = await users.findOne({ email });
  if (existing) return res.status(409).json({ error: "User already exists" });

  const passwordHash = await bcrypt.hash(password, 10);

  const result = await users.insertOne({
    email,
    passwordHash,
    createdAt: new Date(),
  });

  res.status(201).json({ userId: result.insertedId.toString() });
});

// --- Login
app.post("/auth/login", async (req, res) => {
  const { email, password } = req.body || {};
  if (!email || !password) return res.status(400).json({ error: "Missing email or password" });

  const user = await users.findOne({ email });
  if (!user) return res.sendStatus(401);

  const valid = await bcrypt.compare(password, user.passwordHash);
  if (!valid) return res.sendStatus(401);

  const token = jwt.sign({ userId: user._id.toString() }, JWT_SECRET, { expiresIn: "7d" });
  res.json({ token });
});

// --- Add weight (date is user-entered string)
app.post("/weights", auth, async (req, res) => {
  const { weight, unit, date } = req.body || {};
  if (weight === undefined || weight === null || !unit || !date) {
    return res.status(400).json({ error: "Missing weight, unit, or date" });
  }

  const doc = {
    userId: new ObjectId(req.userId),
    weight: Number(weight),
    unit: String(unit),
    date: String(date), // user-entered string
    createdAt: new Date(), // server timestamp for sorting if you want
  };

  await weights.insertOne(doc);
  res.sendStatus(201);
});

// --- Get my weights
app.get("/weights", auth, async (req, res) => {
  const data = await weights
    .find({ userId: new ObjectId(req.userId) })
    .sort({ createdAt: -1 })
    .toArray();

  res.json(data);
});

// --- Delete one of my weights
app.delete("/weights/:id", auth, async (req, res) => {
  const id = req.params.id;
  if (!ObjectId.isValid(id)) return res.status(400).json({ error: "Invalid id" });

  await weights.deleteOne({
    _id: new ObjectId(id),
    userId: new ObjectId(req.userId),
  });

  res.sendStatus(204);
});

// --- Start
async function start() {
  await client.connect();
  const db = client.db(DB_NAME);

  users = db.collection("users");
  weights = db.collection("weights");

  // useful indexes
  await users.createIndex({ email: 1 }, { unique: true });
  await weights.createIndex({ userId: 1, createdAt: -1 });

  app.listen(PORT, "0.0.0.0", () => {
    console.log(`API running at http://localhost:${PORT}`);
  });
}

start().catch((err) => {
  console.error(err);
  process.exit(1);
});