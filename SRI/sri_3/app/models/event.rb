class Event < ActiveRecord::Base
  enum event_type: { driver: 0, pit_stop: 1 }
end
