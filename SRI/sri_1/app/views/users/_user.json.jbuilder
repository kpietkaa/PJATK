json.extract! user, :id, :login, :password, :phone_number, :birth_date, :created_at, :updated_at
json.url user_url(user, format: :json)
