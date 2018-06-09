set :output, Dir.pwd + '/log/cron.log'
set :chronic_options, hours24: true
set :environment, 'development'

every '* * * * *' do
  runner 'RacingCar.perform_async'
end
