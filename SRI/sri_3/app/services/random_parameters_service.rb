class RandomParametersService
  def self.call
    params = {
      engine_temperature: rand(90..200),
      tires_pressure:    rand(0..30),
      oil_pressure:      rand(0..90),
      time:              DateTime.now.strftime('%d-%m-%Y %H:%M:%S:%L') }.to_s
    EventBGBus.announce(:racing_car, params: params)
  end
end
