import json
import joblib

from flask import Flask, request

app = Flask(__name__)

#load ML Model here

###################

@app.route("/hello")
def hello_world():
    return "Hello, World!"

@app.route("/getItems", methods=["GET"])
def getItems():

    response_json = {
        "data": {
            "name": "ererer"
        }
    }


    return json.dumps(response_json)

@app.route("/predict", methods=["POST"])
def predict():
    request_json = request.json
    #bentuk json yg diterima = itemName, price
    print("data: {}".format(request_json))
    print("type: {}".format(type(request_json)))

    prediction = loaded_ML.predict(request_json.get('data'))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)