module RedisJob
  class Pusher
    def self.push_to_notifications(params, worker_klass)
      RedisJob::Pusher.push_to_queue('notifications', params, worker_klass)
    end

    def self.push_to_decisions(params, worker_klass)
      RedisJob::Pusher.push_to_queue('decisions', params, worker_klass)
    end

    def self.push_to_queue(queue_name, params, worker_klass)
      # using << rather than + because it cats instead of newing up string objects
      # "queue:#{queue_name}" - to create sidekiq queue
      redis_url = 'redis://' << '127.0.0.1' << ':' << '6379'

      msg = { 'class': worker_klass.to_s, params: params }
      redis = Redis.new(url: redis_url)
      redis.lpush("#{queue_name}", JSON.dump(msg))
    end
  end
end
