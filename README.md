# RecheckApps - Machine Learning for Budget Mark-up Detection
## Bangkit 2021 Capstone Project - B21-CAP0304

### Background
Have you looked at the news today? Where is the social grant more dangerous than the COVID-19? Where people can't get their social grant in good accuracy? Where is our government taking 100 billion as their mattress to sleep? In a world with millions of data, we want to re-establish our government financial system by reducing the budget plan's chance of being marked-up. In the steps of our government being transparent, we can implement this system by allowing our representatives to check again the proposed budget plan if there is anything that is beyond the allowed range. Doubt the impact? How many children can we help with 100 billion from a corruption of COVID-19 social grant? Seems small yet impactful for people in need.

### Our Team
1. A1201529 - Bagus Bayu Sasongko
2. A1201528 - Ananda Rifkiy Hasan
3. C1201530 - Ajeng Fitria Rahmawati
4. C2832586 - Ainun Ilma
5. M0020057 - Luthfi Eko Trinowo
6. M0020058 - Ismail Faizal Aziz


## Built Apps
IDE : Android Studio
Programming Language : Kotlin
Library : 
 - Room : For local database
 - Retrofit : For call APIs
 - Android Lifecycle : Manage Apps Architecture



## Endpoint API

|Method | Endpoint | Body | Description | Return
|--|--|--|--|--|
| Get | /getitems | - | Get list item that available to test | Json Array Object with attribute item name and item brand
| Post| /predict | { itemName : String, brand : String, price : Int } | Post list item with price to test | 0 = Not Overprice, 1 = Overprice
