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
authe = firebase.auth()
database = firebase.database()

def Login(request):

	return render(request, "Login.html")
#class ProductsPage(generic.TemplateView):
def postsign(request):
     email = request.POST.get('email')
     password = request.POST.get("password")
     try:
         #allowing only authorised users to log in to the site
         user = authe.sign_in_with_email_and_password(email,password)
     except:
         message = "Invalid Credentials"
         return render(request, "Login.html", {"msg":message})
     print(user['idToken'])
     session_id = user['idToken']
     request.session['uid'] = str(session_id)

     #passing the name in the templates
     #response = requests.get('https://api.myjson.com/bins/1ajw1q')
     #data = response.json()
     #return render(request, "Welcome.html", {
     #'products': data['products'[{'name': data['name'],
     #'image': data['image'],
     #'price': data['price'],
     #'description': data['description']
     #}]]
         
         
     #})
     #class BooksPage(generic.TemplateView):
    
     products_list = services.get_products('name', 'image', 'price', 'description')
     return render(request,"Welcome.html",products_list)
     #return render(request, "Welcome.html", {"e":email})
 
#def logout(request):
    #auth.logout(request)
    #return render(request,"Login.html")

def SignUp(request):
    return render(request, "SignUp.html")

def WithoutRegistration(request):
    return render(request, "Welcome.html")

def postSignUp(request):
    email = request.POST.get('email')
    password = request.POST.get("password")
    
    try:
        user = authe.create_user_with_email_and_password(email,password)
    except:
        message = "Unable to create account, please try again later"
        return render(request,"SignUp.html",{"msg":messsage})
        uid = user['localId']

    #data = {"e":email,"status":"1"}

    #database.child("users").child(uid).child("details").set(data)
    return render(request, "Login.html")

def search(request):
    return HttpResponse("we're at search")
    

def productView(request):
    return HttpResponse("we're at product view")



def checkout(request):
    return HttpResponse("we're at checkout")









