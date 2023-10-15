const mongoose = require('mongoose');

const DB_USER = 'user1';
const DB_NAME = 'db1';
const DB_PASS = 'samchrotrazmi';
const DB_HOST = 'rc1a-wapwm4n9upm303x3.mdb.yandexcloud.net';
const DB_PORT = 27018;
const CACERT = "C:\\Users\\rafa-\\Downloads\\RootCA.pem";

// Подключение к базе данных
mongoose.connect(`mongodb://${DB_USER}:${DB_PASS}@${DB_HOST}:${DB_PORT}/${DB_NAME}`, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  ssl: true,
  sslCA: CACERT,
});

const db = mongoose.connection;

db.on('error', (err) => {
  console.error('Database connection error:', err);
});

db.once('open', () => {
  console.log('Connected to database');
});

module.exports = db;
