class User < ApplicationRecord
  after_commit :flush_cache!, on: [:create, :update, :destroy]

  scope :by_id, ->(id) do
    Rails.cache.fetch("users_#{id}") { where(id: id) }
  end

  def self.search(term)
    if term
      by_id(term)
    else
      order(created_at: :asc)
    end
  end

  private
    def flush_cache!
      puts 'Flushing the cache...'
      Rails.cache.clear
    end
end
