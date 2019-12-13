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
database = services.firebase_key().database()

def Login(request):

	return render(request, "Login.html")

   
def postsign(request):
     
     data = services.get_products()
     print(data)

     context = {'data': data}
     number = request.POST.get('number')
     password = request.POST.get("password")
     
     #retrieving the index numbers from the database
     user = database.child("users").shallow().get().val()
     

     list_user = []
     for i in user:
        #adding each user number to the list
         list_user.append(i)

    
     #checking if the number posted exists in the database
     if number in list_user:
          return render(request, "Welcome.html",context)

     else:   
        message="Invalid Credentials"

        return render(request,"Login.html",{"msg":message})

     

def SignUp(request):
    return render(request, "SignUp.html")


def WithoutRegistration(request):
    
    data = services.get_products()
    context = {'data': data}
    return render(request,"Welcome.html",context)
     

def postSignUp(request):
    email = request.POST.get('email')
    password = request.POST.get("password")
    number = request.POST.get('number')
    
    #creating users
    try:
        user = authe.create_user_with_email_and_password(email,password)
    except:
        message = "Unable to create account, please try again later"
        return render(request,"SignUp.html",{"msg":messsage})
        uid = user['localId']

    data = {"email":email, "password":password, "phone":number}

    database.child("users").child(number).set(data)


    return render(request, "Login.html")

def search(request):
    return HttpResponse("we're at search")
    

def productView(request):
    #Fetch the product using the id

    data = services.get_products()

    return render(request, "productView.html")



def checkout(request):
    return HttpResponse("we're at checkout")









