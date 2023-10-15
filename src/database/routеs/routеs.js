const express = require('express');
const Office = require('../models/OfficeModel');

const router = express.Router();


router.get('/offices', async (req, res) => {
    try {
        const offices = await Office.find();
        res.json(offices);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Ошибка при получении офисов' });
    }
});

module.exports = router;

router.post('/findOffices', async (req, res) => {
    try {
      const { latitude, longitude } = req.body; 
  
      
      const offices = await Office.find({
        location: {
          $near: {
            $geometry: {
              type: 'Point',
              coordinates: [longitude, latitude], 
            },
          },
        },
      }).sort({ occupancy: 1 });
  
      res.json(offices);
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: 'Server error' });
    }
  });
