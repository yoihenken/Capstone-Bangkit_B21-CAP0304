import json
import xgboost as xgb
import pandas as pd
from joblib import load
from sklearn.metrics import accuracy_score
from flask import Flask, request

app = Flask(__name__)


bst = xgb.XGBClassifier()  # init model
bst.load_model('overprice.bin')  # load data
print("Loaded model from: overprice.bin")

init_df = pd.DataFrame(columns=['prices.amountMax', 'prices.condition_NEW', 'brand_Apple',
       'brand_Corsair', 'brand_Olympus', 'brand_Papago', 'brand_Samsung',
       'brand_SanDisk', 'brand_Sideclick', 'brand_Sony', 'brand_Sound Design',
       'name_15.4 MacBook Pro with Touch Bar (Late 2016, Space Gray)',
       'name_Corsair Vengeance LPX 16GB (2x8GB) DDR4 DRAM 3000MHz C15 Desktop Memory Kit - Black (CMK16GX4M2B3000C15)',
       'name_GoSafe S30 1080p Dash Cam',
       'name_Olympus TG-5 Waterproof Camera with 3-Inch LCD',
       'name_Samsung - Adaptive Fast Charging Wall Charger - White',
       'name_SanDisk Ultra II 1TB SATA III SSD - 2.5-Inch 7mm Height Solid State Drive - SDSSDHII-1T00-G25',
       'name_Sideclick - Universal Remote Attachment for Roku® Streaming Players - Black',
       'name_Sony - BC-TRX Battery Charger - Black',
       'name_Sony HTST9 Soundbar with Wireless Subwoofer Bluetooth and Google Cast',
       'name_iHome iBN43BC Bluetooth Stereo Dual Alarm FM Clock Radio and Speakerphone with USB Charging'])

@app.route("/")
def hello_world():
    return "Hello, World!"

@app.route("/getItems", methods=["GET"])
def getItems():
    response_json = {
        "data": [
            {
                "itemName": "15.4 MacBook Pro with Touch Bar (Late 2016, Space Gray)",
                "brand": "Apple"
            },
            {
                "itemName": "Corsair Vengeance LPX 16GB (2x8GB) DDR4 DRAM 3000MHz C15 Desktop Memory Kit - Black (CMK16GX4M2B3000C15)",
                "brand": "Corsair"
            },
            {
                "itemName": "GoSafe S30 1080p Dash Cam",
                "brand": "Papago"
            },
            {
                "itemName": "Olympus TG-5 Waterproof Camera with 3-Inch LCD",
                "brand": "Olympus"
            },
            {
                "itemName": "Samsung - Adaptive Fast Charging Wall Charger - White",
                "brand": "Samsung"
            },
            {
                "itemName": "SanDisk Ultra II 1TB SATA III SSD - 2.5-Inch 7mm Height Solid State Drive - SDSSDHII-1T00-G25",
                "brand": "SanDisk"
            },
            {
                "itemName": "Sideclick - Universal Remote Attachment for Roku® Streaming Players - Black",
                "brand": "Sideclick"
            },
            {
                "itemName": "Sony - BC-TRX Battery Charger - Black",
                "brand": "Sony"
            },
            {
                "itemName": "Sony HTST9 Soundbar with Wireless Subwoofer Bluetooth and Google Cast",
                "brand": "Sony"
            },
            {
                "itemName": "iHome iBN43BC Bluetooth Stereo Dual Alarm FM Clock Radio and Speakerphone with USB Charging",
                "brand": "Sound Design"
            },
        ]
    }
    return json.dumps(response_json)

@app.route("/predict", methods=["POST"])
def predict():
    request_json = request.get_json()
    res = []

    for i in request_json["data"]:
        predict_df = pd.DataFrame()
        
        newItem = { 
            "name": i["itemName"], 
            "brand": i["brand"], 
            "prices.amountMax": i["price"], 
            "prices.condition": "NEW"
        }
        predict_df = predict_df.append(newItem, ignore_index=True)
        # creating the dataFrame buat predict
        predict_df = pd.get_dummies(predict_df)
        predict_df = predict_df.append(init_df, ignore_index=True)
        predict_df = predict_df.fillna(0)
        #predict
        try:
            predictions = bst.predict(predict_df)
            predictions = predictions.tolist()
            res.extend(predictions)
        except:
            print("prediction has failed.")
    response_json = {
        "data": res
    }
    return json.dumps(response_json)    

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80)