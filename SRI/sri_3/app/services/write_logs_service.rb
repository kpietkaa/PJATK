class WriteLogsService
  attr_accessor :params

  def initialize(data)
    @params = eval(data['params'])
  end

  def call
    write_to_file
  end

  private

  def write_to_file
    begin
      file = File.open(Rails.root.join('log', 'logs_from_race.log'), 'a+')
      file.puts(log)
    rescue IOError => e
      Rails.logger.info(e)
    ensure
      file.close unless file.nil?
    end
  end

  def log
    log = ''
    log << @params[:time] + ' * '
    log << "Engine temperature: #{@params[:engine_temperature]}, "
    log << "Tires pressure: #{@params[:tires_pressure]}, "
    log << "Oil pressure: #{@params[:oil_pressure]}"
  end
end
