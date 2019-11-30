from django.db import models

class Product(models.Model):
    name = models.CharField(max_length=25, default="")
    price = models.IntegerField(default=0)
    description = models.CharField(max_length=300, default="")

     

    def __str__(self):
        return self.name
        

    class Meta:
        verbose_name_plural = 'products'