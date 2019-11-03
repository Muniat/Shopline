from django.shortcuts import render
from django.views import View
from django.views.decorators.cache import cache_page
from django.views.decorators.csrf import csrf_protect
import pyrebase
from django.contrib import auth

config = {
    'apiKey': "AIzaSyCCII1unlXf1oWgOZSWsl-LRR6Ul5yGs78",
    'authDomain': "webapp-f4ea1.firebaseapp.com",
    'databaseURL': "https://webapp-f4ea1.firebaseio.com",
    'projectId': "webapp-f4ea1",
    'storageBucket': "webapp-f4ea1.appspot.com",
    'messagingSenderId': "667365154761",
    'appId': "1:667365154761:web:7f979a5d6efa54934ed218",
    'measurementId': "G-5RDEDDX157"

	}
firebase = pyrebase.initialize_app(config)
authe = firebase.auth()

def Login(request):

	return render(request, "Login.html")

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
    return render(request, "Welcome.html", {"e":email})
 
def logout(request):
    auth.logout(request)
    return render(request,"Login.html")

#def signUp(request):
    #return render(request, "SignUp.html")

