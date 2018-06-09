class DecisionService
  def call
    connect_to_redis
    fetch_request
  end

  private

  def fetch_request
    JSON.parse(@redis.lpop('notifications'))['params'].symbolize_keys if @redis.llen('notifications') > 0
  end

  def connect_to_redis
    redis_url = 'redis://' << '127.0.0.1' << ':' << '6379'
    @redis = Redis.new(url: redis_url)
  end
end
