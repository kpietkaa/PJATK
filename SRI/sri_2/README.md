== README

Web application use stateless, stateful and singleton design patterns.

## Singleton pattern

Used to store Categories across whole app. Reload after update/delete of the Category.
User don't call categories direct from DB.   

Implementation: `config/initializers/singleton/categories.rb`

Initialization: `config/initializers/categories.rb`

## Stateful pattern

Used to implement OrderItem.

Possible states:
* empty_cart (initial)
* cart_with_products
* order_saved

Allowed statuses:
* add_product
* create_order
* cancel

Allowed status changes:
* from: `empty_cart`, to: `cart_with_products` (add_product)
* from: `cart_with_products`, to: `cart_with_products` (add_product)
* from: `cart_with_products`, to: `order_saved` (create_order)
* from: `cart_with_products`, to: `empty_cart` (cancel)
