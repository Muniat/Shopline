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
import pprint



authe = services.firebase_key().auth()
database = services.firebase_key().database()

def Login(request):

	return render(request, "Login.html")

   
def postsign(request):
     if request.method == 'GET' and 'csrfmiddlewaretoken' in request.GET:

       search = request.GET.get('Search')
       search = search.lower()
       timestamps = database.child("Products").shallow().get().val()
       
       list_time = []
       for i in timestamps:
         list_time.append(i)

       pcategory = []
       for i in timestamps:
           category = database.child("Products").child(i).child("category").get().val()
           category = str(category)+"$"+str(i)
           pcategory.append(category)
       matching =[str(string) for string in pcategory if search in string.lower()]


       s_category = []
       s_id = [] 

       for i in matching:
          category,ids=i.split("$")
          s_category.append(category)
          s_id.append(ids)
       

       print(s_category)
       data = services.get_products()
    
       

       return render(request, "result.html",{'category':s_category, 'data':data})



     else:
        data = services.get_products()
        print(data)

        #context = {'data': data}
        number = request.POST.get('number')
        password = request.POST.get("password")
     
        list_user = services.get_users()

    
        #checking if the number posted exists in the database
        if number in list_user:
             return render(request, "Welcome.html",{'data':data})

        else:   
           message="Invalid Credentials"

           return render(request,"Login.html",{"msg":message})
        


     

     



def SignUp(request):
    return render(request, "SignUp.html")


def WithoutRegistration(request):
    
    data = services.get_products()
    context = {'data': data}
    return render(request,"withoutReg.html",context)
     

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
    

def productView(request):
    #Fetch the product using the id

    data = services.get_products()

    return render(request, "productView.html")



def checkout(request):
    return HttpResponse("we're at checkout")

def cart(request):
    #print(request.session)
    #print(dir(request.session))
    #key = request.session.session_key
    #print(key)
    #request.session.set_expiry(300)
    cart_id = request.session.get("cart_id",None)
    if cart_id is None: #and isinstance(cart_id,int):
        pass
        print('create new cart')
        request.session['cart_id'] = 12 #SET
    else:
        print('Cart ID exists')
    #print (request.session.get("first_name", "Unknown"))
    return render(request,"cart.html",{})









