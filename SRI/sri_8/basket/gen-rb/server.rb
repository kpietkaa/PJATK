$:.push('gen-rb')
$:.unshift '../../lib/rb/lib'

require 'thrift'
require_relative 'order'

class OrderHandler
  def initialize
    @order = []
  end

  def add_product_to_order(product, amount)
    puts '###################################################################'
    if product.amount >= amount
      product.amount = amount
      @order.push(product)
      puts "Add product #{product.inspect}"
      puts "All products: #{@order.inspect}"
      1
    else
      puts 'Not enough products in store.'
      0
    end
  end

  def remove_product_from_order(product)
    puts '###################################################################'
    @order.delete_if{ |p| p.id == product.id}
    puts "Other products: #{@order.inspect}"
    @order
  end

  def update_amount(product, amount)
    puts '###################################################################'
    if product.amount >= amount
      product.amount = amount
      @order.delete_if{ |p| p.id == product.id}
      @order.push(product)
      puts "Add product #{product.inspect}"
      puts "All products: #{@order.inspect}"
      1
    else
      puts 'Not enough products in store.'
      0
    end
  end

  def confirm_order
    puts '###################################################################'
      'Order confirmed.'
  end
end

handler = OrderHandler.new
processor = Order::Processor.new(handler)
transport = Thrift::ServerSocket.new(9090)
transport_factory = Thrift::BufferedTransportFactory.new
server = Thrift::SimpleServer.new(processor, transport, transport_factory)

puts 'Starting the server...'
server.serve
puts 'done.'
