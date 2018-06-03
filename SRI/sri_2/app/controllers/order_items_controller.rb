class OrderItemsController < ApplicationController
  protect_from_forgery with: :null_session

  # { products => [[id: 2000, amount: 1], [id:2001, amount: 2]] }
  def create
    Product.transaction do
      @order = OrderItem.new(DateTime.now.strftime('%d-%m-%Y %H:%M'))
      add_products
      result = @order.create_order
      update_products_stock if result
    end
    render json: { name: @order.name,
                   state: @order.aasm.current_state,
                   products: @order.products_list.as_json }
  rescue AASM::InvalidTransition => e
    Rails.logger.info(e)
    render json: { messgae: 'Invalid params' }
  end

  private

  def order_items
    params.permit(:products)
  end

  def add_products
    eval(order_items[:products]).each do |product|
      @order.add_product(product.first[:id], product.first[:amount])
    end
  end

  def update_products_stock
    eval(order_items[:products]).each do |product|
      product_update = Product.find(product.first[:id])
      stock = product_update.stock - product.first[:amount]
      product_update.update(stock: stock)
    end
  end
end
