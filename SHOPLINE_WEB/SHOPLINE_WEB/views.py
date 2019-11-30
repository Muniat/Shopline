from django.shortcuts import render
from django.http import HttpResponse
from django.views import View
from django.views.decorators.cache import cache_page
from django.views.decorators.csrf import csrf_protect
import pyrebase
from django.contrib import auth
import json
import requests
from . import services
from django.views.generic import TemplateView
from .models import Product
import pprint



authe = services.firebase_key().auth()


def Login(request):

	return render(request, "Login.html")

   
def postsign(request):

     email = request.POST.get('email')
     password = request.POST.get("password")
     try:
         #allowing only authorised users to log in to the site
         user = authe.sign_in_with_email_and_password(email,password)
     except:
         msg = "Invalid Credentials"
         return render(request, "Login.html", {"msg":msg})
     print(user['idToken'])
     session_id = user['idToken']
     request.session['uid'] = str(session_id)

    

     data = []
     
     jsonData = services.get_products()

     for products in jsonData:

        productData = {}

       
        productData['name'] = products['name']
        productData['image'] = products['image']
        productData['price'] = products['price']
        productData['description'] = products['description']
        
        data.append(productData)
     print(data)

     context = {'data': data}
     return render(request,"Welcome.html",context)
     


def SignUp(request):
    return render(request, "SignUp.html")

def WithoutRegistration(request):
     data = []
     
     jsonData = services.get_products()

     for products in jsonData:

        productData = {}

       
        productData['name'] = products['name']
        productData['image'] = products['image']
        productData['price'] = products['price']
        productData['description'] = products['description']
        
        data.append(productData)
     print(data)

     context = {'data': data}
     return render(request,"Welcome.html",context)
     

def postSignUp(request):
    email = request.POST.get('email')
    password = request.POST.get("password")
    
    try:
        user = authe.create_user_with_email_and_password(email,password)
    except:
        message = "Unable to create account, please try again later"
        return render(request,"SignUp.html",{"msg":messsage})
        uid = user['localId']


    return render(request, "Login.html")

def search(request):
    return HttpResponse("we're at search")
    

def productView(request):
    return HttpResponse("we're at product view")



def checkout(request):
    return HttpResponse("we're at checkout")









