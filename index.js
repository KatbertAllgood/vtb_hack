const express = require('express');
const cors = require('cors');
const db = require('./src/db');
const Service = require('./src/database/models/ServiceModel');
const Office = require('./src/database/models/OfficeModel'); 
const Department = require('./src/database/models/DepartmentModel'); 
require('./src/database/CreateDocuments');
const routes = require('./src/database/routеs/routеs');


const app = express();
const PORT = 5001;

const whiteList = [undefined, 'http://localhost:5001'];

app.use(express.json());
app.use('/api', routes);

app.use(cors({
    credentials: true,
    origin: function(origin, callback) {
        if (whiteList.indexOf(origin) !== -1) {
            callback(null, true);
        }
    },
}));

const startServer = async () => {
    try {
        await db; 
        await Service.init();
        await Office.init();
        await Department.init();

        app.listen(PORT, () => {
            console.log('Server started on PORT:', PORT);
        });
    } catch (error) {
        console.log(error);
    }
};

startServer();
