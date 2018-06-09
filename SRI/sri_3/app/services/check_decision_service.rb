class CheckDecisionService
  def call
    connect_to_redis
    fetch_response
  end

  private

  def fetch_response
    JSON.parse(@redis.lpop('decisions'))['params'].symbolize_keys if @redis.llen('decisions') > 0
  end

  def connect_to_redis
    redis_url = 'redis://' << '127.0.0.1' << ':' << '6379'
    @redis = Redis.new(url: redis_url)
  end
end
