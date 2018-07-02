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
