from django.db import models

#Create your models here

#class Product(models.Model):
	#product_id = models.AutoField
	#product_name = models.CharField(max_length=50)
	#category = models.CharField(max_length=50, default="")
	#subcategory = models.CharField(max_length=50, default="")
	#price = models.IntegerField(default=0)
	#description = models.CharField(max_length=300)
	#pub_date = models.DateField()
	#image = models.ImageField(upload_to="static/images", default="")

	#def __str__(self):
		#return self.product_name
class Product(models.Model):
	name = models.CharField(max_length=25, default="")

	def __str__(self):
		return self.name

	class Meta:
		verbose_name_plural = 'products'