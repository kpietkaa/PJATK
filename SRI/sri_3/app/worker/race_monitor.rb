class RaceMonitor
  include Sidekiq::Worker

  def perform
    EventBus.subscribe('racing_car') do |data|
      MonitoringService.new(data).call
    end
  end
end
