class MonitoringService
  attr_accessor :params, :exceeded_values

  MAX_SAVE_PARAMS = {
    engine_temperature: 190,
    tires_pressure:      28,
    oil_pressure:        85
  }

  def initialize(data)
    @params = eval(data['params'])
    @exceeded_values = []
  end

  def call
    inform_driver if params_exceeded?
    inform_mechanics if car_breakdown?
  end

  private

  def params_exceeded?
    @params.each_pair do |key, val|
      next if key == :time
      @exceeded_values << { "#{key}": val } if val > MAX_SAVE_PARAMS[key]
    end
    return true if @exceeded_values.present?
    false
  end

  def car_breakdown?
    return true if @exceeded_values.size > 1
    false
  end

  def inform_driver
    Faraday.post('http://localhost:3002/driver_notifications', values: @exceeded_values )
  end

  def inform_mechanics
    Faraday.post('http://localhost:3002/pit_stop_notifications', values: @exceeded_values )
  end
end
