import json
import xgboost as xgb
from joblib import load
from sklearn.metrics import accuracy_score
from flask import Flask, request

app = Flask(__name__)


bst = xgb.Booster()  # init model
loaded_model = bst.load_model('overprice.bin')  # load data
print("Loaded model from: overprice.bin")


@app.route("/")
def hello_world():
    return "Hello, World!"

@app.route("/getItems", methods=["GET"])
def getItems():

    brands = ['Apple', 'Corsair', 'Olympus', 'Papago', 'Samsung', 'SanDisk', 'Sideclick', 'Sony', 'SoundDesign']
    itemName = [
        '15.4 MacBook Pro with Touch Bar (Late 2016, Space Gray)',
        'Corsair Vengeance LPX 16GB (2x8GB) DDR4 DRAM 3000MHz C15 Desktop Memory Kit - Black (CMK16GX4M2B3000C15)',
        'GoSafe S30 1080p Dash Cam',
        'Olympus TG-5 Waterproof Camera with 3-Inch LCD',
        'Samsung - Adaptive Fast Charging Wall Charger - White',
        'SanDisk Ultra II 1TB SATA III SSD - 2.5-Inch 7mm Height Solid State Drive - SDSSDHII-1T00-G25',
        'Sideclick - Universal Remote Attachment for Roku® Streaming Players - Black',
        'Sony - BC-TRX Battery Charger - Black',
        'Sony HTST9 Soundbar with Wireless Subwoofer Bluetooth and Google Cast',
        'iHome iBN43BC Bluetooth Stereo Dual Alarm FM Clock Radio and Speakerphone with USB Charging'
    ]
    response_json = {
        "data": {
            "brands" : brands,
            "itemName" : itemName
        }
    }
    return json.dumps(response_json)

@app.route("/predict", methods=["POST"])
def predict():

    #cek gimana CARA POST WKWKWK
    request_json = request.get_json()

    print(request_json)
    #bentuk json yg diterima = json 
    request_json = {[
        {
            'itemName': itemName,
            'price': price 
        }, 
        {
            'itemName': itemName,
            'price': price
        }]}


    print("data: {}".format(request_json))
    print("type: {}".format(type(request_json)))

    # iterasi per item and make prediction
    # make prediction
    prediction = loaded_model.predict(request_json.get('data'))
    print(prediction)
    response_json = {[
        
    ]}

    return json.dumps(response_json)    

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000) 