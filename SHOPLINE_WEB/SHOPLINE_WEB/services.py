import requests
from . import views
import json
from .models import Product
import pyrebase

def get_products():

    product_list = Product.objects.all()

    r = requests.get('https://api.myjson.com/bins/vvjiu')
    jsonList = r.json()
    jsonData = jsonList['products']

    return jsonData

def firebase_key():

	config = {
    'apiKey' : "AIzaSyBd31FZCtpdRuxmkY0uiitJap1Mcet1iDA",
    'authDomain' : "registration-c3373.firebaseapp.com",
    'databaseURL' : "https://registration-c3373.firebaseio.com",
    'projectId' : "registration-c3373",
    'storageBucket' : "registration-c3373.appspot.com",
    'messagingSenderId' : "123962731629",
    'appId' : "1:123962731629:web:c4cc2ed965b1031daa2364"
	}

	firebase = pyrebase.initialize_app(config)

	return firebase