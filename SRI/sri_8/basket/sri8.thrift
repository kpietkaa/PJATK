namespace java sri8

struct Product {
 1: i32    id,
 2: string name,
 3: i32    price,
 4: i32    amount,
}

service Order {
  i32 add_product_to_order(1:Product product, 3:i32 amount),
  list<Product> remove_product_from_order(1:Product product),
  i32 update_amount(1:Product product, 2:i32 amount),
  string confirm_order()
}
