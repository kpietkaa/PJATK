## Apache Thrift application

#### Prerequisites

`bundle install`

#### Generate code
`thrift -r --gen rb sri8.thrift`

#### Run applicaiton
Go to generated directory `gen-rb` and create client & server applications:

`server.rb`:
```ruby
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
```

`client.rb`:
```ruby
$:.push('gen-rb')
$:.unshift '../../lib/rb/lib'

require 'thrift'
require 'faker'
require_relative 'order'

begin
  port = 9090

  @products = []
  10.times do |i|
    @products << Product.new(id: i + 1,
                name: Faker::Commerce.product_name,
                price: Faker::Number.between(10, 100),
                amount: Faker::Number.between(2, 10))
  end

  transport = Thrift::BufferedTransport.new(Thrift::Socket.new('localhost', port))
  protocol = Thrift::BinaryProtocol.new(transport)
  client = Order::Client.new(protocol)

  transport.open

  client.add_product_to_order(@products[0], 2)
  client.add_product_to_order(@products[2], 1)


  puts client.remove_product_from_order(@products[0]).inspect

  client.update_amount(@products[0], 5)

  puts client.confirm_order

  transport.close

rescue Thrift::Exception => tx
  puts 'Thrift::Exception: ' + tx.message
end
```

#### Run applicaiton
Server: `ruby server.rb`

Client: `ruby client.rb`
