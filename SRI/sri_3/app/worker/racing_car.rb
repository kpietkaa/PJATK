class RacingCar
  include Sidekiq::Worker

  def perform
    RandomParametersService.call
  end
end
