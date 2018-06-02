module Singleton
  class Categories
    include Singleton
    attr_writer :categories

    def reset
      @categories = nil
    end

    def all
      @categories || raise("Categories not set")
    end

    def fetch_from_db
      @categories = Category.all
    end
  end
end
