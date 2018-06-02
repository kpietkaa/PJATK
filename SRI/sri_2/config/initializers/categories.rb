require_relative 'singleton/categories'

Rails.application.config.categories = Singleton::Categories.instance
Rails.application.config.categories.fetch_from_db
