import requests
from . import views
import json

import pyrebase

def get_products():
    
    database = firebase_key().database()
   #product_list = Product.objects.all()
   

    data = []

    #fetching data from the database
    all_products = database.child("Products").get()
    for product in all_products.each():
        #adding each product to the list 'data'
        data.append(product.val())
    
    

    return data

def get_users():
    database = firebase_key().database()
    
    user = database.child("users").shallow().get().val()
     

    list_user = []
    for i in user:
        #adding each user number to the list
        list_user.append(i)

    return list_user


def firebase_key():
    
    #configuring firebase
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
