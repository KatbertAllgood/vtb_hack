// ServiceModel.js
const mongoose = require('mongoose');

const { Schema } = mongoose;

const serviceSchema = new mongoose.Schema({
  name: String,
  occupancy: Number,
});

const Service = mongoose.model('Service', serviceSchema);
module.exports = Service;
