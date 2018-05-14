## README

#### Boot the project

    rake db:create 
    rake db:migrate
    rake db:seed
    rails s
    
#### Wsdl routes
    
    rake routes
  
  
## Connect to sever endpoints

    gem install savon
    
  or
  
    bundle # in project directory
    
#### Then:
  
```ruby
  require 'savon'
  
  # create a client for the service
  client = Savon.client(wsdl: 'http://localhost:3000/comments/wsdl')
  
  client.operations
  # => [:index, :show, :create, :update, :destroy]
  
  # call the 'create' operation
  response = client.call(:create, message: {body: 'Hello World', 'post_id' => 4, 'user_id' => 2}) 
  
  response.body
  # => {:create_response=>{:value=>"OK"}}
```
