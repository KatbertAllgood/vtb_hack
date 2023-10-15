from flask import Flask, request, jsonify
import cv2
import numpy as np
import os
from werkzeug.utils import secure_filename

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = './image'
allowed_extensions = {'jpg', 'jpeg', 'png'}

net = cv2.dnn.readNet('./yolov3.weights', './yolov3.cfg')

with open('./coco.names', 'r') as f:
    classes = f.read().strip().split('\n')

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in allowed_extensions

@app.route('/detect_people', methods=['POST'])
def detect_people():
    if 'image' not in request.files:
        return jsonify({"error": "No image file provided"})

    image_file = request.files['image']

    if image_file.filename == '':
        return jsonify({"error": "No selected file"})

    if image_file and allowed_file(image_file.filename):
        filename = secure_filename(image_file.filename)
        image_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        image_file.save(image_path)

        img = cv2.imread(image_path)
        height, width, _ = img.shape

        blob = cv2.dnn.blobFromImage(img, 1/255.0, (416, 416), swapRB=True, crop=False)
        net.setInput(blob)

        layer_names = net.getUnconnectedOutLayersNames()
        outs = net.forward(layer_names)

        people_count = 0
        detections = []

        for out in outs:
            for detection in out:
                scores = detection[5:].astype(float)
                class_id = np.argmax(scores)
                confidence = scores[class_id]
                if confidence > 0.4 and classes[class_id] == 'person':
                    center_x = int(detection[0] * width)
                    center_y = int(detection[1] * height)
                    w = int(detection[2] * width)
                    h = int(detection[3] * height)
                    x = center_x - w // 2
                    y = center_y - h // 2
                    detections.append([x, y, w, h, confidence])

        if len(detections) > 0:
            detections = np.array(detections) 
            indices = cv2.dnn.NMSBoxes(detections[:, :4], detections[:, 4], 0.5, 0.4)

            people_count = len(indices)
            return jsonify({"people_count": people_count})
        else:
            return jsonify({"people_count": 0, "message": "No people detected"})

if __name__ == '__main__':
    if not os.path.exists(app.config['UPLOAD_FOLDER']):
        os.makedirs(app.config['UPLOAD_FOLDER'])
    app.run(host='0.0.0.0', port=6000)
