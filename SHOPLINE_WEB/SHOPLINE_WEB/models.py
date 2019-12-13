from django.db import models

#Define your models here

class Product(models.Model):
    name = models.CharField(max_length=25, default="")
   
    product_id = models.AutoField

     
    def __str__(self):
        return self.name
        

    class Meta:
        verbose_name_plural = 'products'