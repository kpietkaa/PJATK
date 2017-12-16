ENV['RACK_ENV'] ||= 'test'

require 'rack/test'
require 'rspec'
require 'factory_bot'
require 'faker'
require 'pry'
require 'database_cleaner'

module RSpecMixin
  include Rack::Test::Methods

  def app
    described_class
  end
end

RSpec.configure do |config|
  config.include RSpecMixin
  config.include FactoryBot::Syntax::Methods

  config.before(:suite) do
    I18n.locale = :en
    DatabaseCleaner.strategy = :transaction
    DatabaseCleaner.clean_with(:truncation)
  end

  config.around(:each) do |example|
    DatabaseCleaner.cleaning do
      example.run
    end
  end
end

FactoryBot.definition_file_paths = %w{./spec/factories}
FactoryBot.find_definitions
