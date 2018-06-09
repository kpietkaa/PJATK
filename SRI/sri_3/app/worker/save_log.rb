class SaveLog
  include Sidekiq::Worker

  def perform
    EventBus.subscribe('racing_car') do |data|
      WriteLogsService.new(data).call
    end
  end
end
