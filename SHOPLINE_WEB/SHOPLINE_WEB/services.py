import requests

def get_products(name, image, price, description):
    url = 'https://api.myjson.com/bins/1ajw1q' 
    params = {'name': name, 'image': image, 'price': price, 'description':description}
    r = requests.get(url, params=params)
    product = r.json()
    products_list = {'product':product['products']}
    return products_list