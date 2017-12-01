require 'sinatra'
require 'sinatra/cross_origin'
require 'sinatra/activerecord'
require './config/environments'
require './config/cors'
require './models/task'
require 'json'

before do
  content_type :json
end

get '/' do
  content_type :html
  send_file './public/index.html'
end

get '/tasks' do
  Task.all.to_json
end

get '/tasks/:id' do
  Task.where(id: params['id']).first.to_json
end

post '/tasks' do
  task = Task.new(params)

  if task.save
    task.to_json
  else
    halt 422, task.errors.full_messages.to_json
  end
end

put '/tasks/:id' do
  task = Task.where(id: params['id']).first

  if task
    task.name     = params['name']     if params.has_key?('name')
    task.price    = params['price']    if params.has_key?('price')
    task.quantity = params['quantity'] if params.has_key?('quantity')

    if task.save
      task.to_json
    else
      halt 422, task.errors.full_messages.to_json
    end
  end
end

delete '/tasks/:id' do
  task = Task.where(id: params['id'])

  if task.destroy_all
    { success: "ok" }.to_json
  else
    halt 500
  end
end

get '/refresh' do
  # Clean the database and create the initial data
  load './db/seeds.rb'
end

after do
  # Close the connection after the request is done so that we don't
  # deplete the ActiveRecord connection pool.
  ActiveRecord::Base.connection.close
end