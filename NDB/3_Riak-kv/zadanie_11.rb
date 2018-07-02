require 'riak'

class RiakConnection
  def initialize
    @client = Riak::Client.new
  end

  def show(bucket, key)
    object = @client[bucket][key]
    {
      bucket: object.bucket.name,
      key:    object.key,
      data:   object.content.raw_data
    }
  end

  def create(bucket, key, data)
    bucket = @client.bucket(bucket)
    new = Riak::RObject.new(bucket, key)
    new.content_type = 'application/javascript'
    new.raw_data = data
    new.store
  end

  def update(bucket, key, data)
    begin
      object = @client.bucket(bucket).get(key)
      object.raw_data = data
      object.store
    rescue Riak::ProtobuffsFailedRequest
      'Expected success from Riak but received not_found. The requested object was not found.'
    end
  end

  def destroy(bucket, key)
    begin
      object = @client.bucket(bucket).get(key)
      object.delete
    rescue Riak::ProtobuffsFailedRequest
      'Expected success from Riak but received not_found. The requested object was not found.'
    end
  end
end

obj = RiakConnection.new
puts 'Creating new object....'
obj.create('ruby', 'app.js', "alert('Hello, World!')")

puts 'Object:'
puts obj.show('ruby', 'app.js')

puts 'Updating object....'
obj.update('ruby', 'app.js', 'if(rows == "" || rows == null)')

puts 'After update:'
puts obj.show('ruby', 'app.js')

puts 'Deleting object....'
puts obj.destroy('ruby', 'app.js')

puts 'Deleting same object again....'
puts obj.destroy('ruby', 'app.js')
puts 'The end.'

