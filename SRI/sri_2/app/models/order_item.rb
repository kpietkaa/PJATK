class OrderItem
  include AASM

  attr_reader :name
  attr_accessor :products_list, :valid_to_save

  def initialize(name)
    @name = name
    @products_list = []
    @valid_to_save = false
  end

  aasm do
    state :empty_cart, initial: true
    state :cart_with_products
    state :order_saved

    event :add_product do
      transitions from: [:empty_cart, :cart_with_products], to: :cart_with_products, after: Proc.new { |*args| save_products(*args) }
    end

    event :create_order do
      transitions from: :cart_with_products, to: :order_saved, if: :check_if_products_available?
    end

    event :cancel do
      transitions from: :cart_with_products, to: :empty_cart, after: :delete_products_from_list
    end
  end

  def save_products(product, amount)
    products_list.push({ product_id: product, amount: amount })
  end

  def check_if_products_available?
    check = @products_list.map do |product|
              begin
                Product.find(product[:product_id]).stock >= product[:amount] ? true : false
              rescue ActiveRecord::RecordNotFound => e
                false
              end
            end
    @valid_to_save = if check.include? false
                       false
                     else
                       true
                     end
  end

  def delete_products_from_list
    @products_list = []
  end
end
