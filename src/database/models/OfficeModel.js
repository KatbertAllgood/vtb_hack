const mongoose = require('mongoose');
const departmentSchema = require(__dirname +'/DepartmentModel').schema;

const { Schema } = mongoose;

const officeSchema = new mongoose.Schema({
  name: String,
  address: String,
  occupancy: Number,
  location: {
    type: {
      type: String,
      enum: ['Point'],
      required: true
    },
    coordinates: {
      type: [Number],
      required: true
    }
  },
  handicapped: Boolean,
  departments: [departmentSchema],
});

officeSchema.index({ location: '2dsphere' });

const Office = mongoose.model('Office', officeSchema);
module.exports = Office;
