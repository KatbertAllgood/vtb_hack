// DepartmentModel.js
const mongoose = require('mongoose');
const serviceSchema = require(__dirname +'/ServiceModel').schema;

const { Schema } = mongoose;

const departmentSchema = new mongoose.Schema({
  type: Number,
  services: [serviceSchema],
});

const Department = mongoose.model('Department', departmentSchema);
module.exports = Department;