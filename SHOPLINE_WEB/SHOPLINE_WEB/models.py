
from django.db import models
from . import views
from . import services


#Define your models here

User = services.get_users()
item = services.get_products()

class Product(models.Model):
    name = models.CharField(max_length=25, default="")
    slug = models.SlugField(unique=True)
     
    def __str__(self):
        return self.name
        

    class Meta:
        verbose_name_plural = 'products'


