Rails.application.routes.draw do
  require 'sidekiq/web'
  mount Sidekiq::Web => '/sidekiq'

  resources :driver_notifications, only: [:create], default: {format: 'json' }
  resources :pit_stop_notifications, only: [:create], default: { format: 'json' }
  resources :pit_stops, only: [:index, :create], default: { format: 'json' }
  resources :head_of_races, only: [:index, :create], default: { format: 'json' }
end
