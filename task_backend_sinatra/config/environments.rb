configure :production, :development do
  set :show_exceptions, true

  db = URI.parse(ENV['DATABASE_URL'] || 'postgres://127.0.0.1/todo')

  ActiveRecord::Base.establish_connection(
    adapter: 	db.scheme == 'postgres' ? 'postgresql' : db.scheme,
    host: 		db.host,
    username: db.user,
    password: db.password,
    database: db.path[1..-1],
    encoding: 'utf8'
  )

  ActiveRecord::Base.class_eval do
    def self.reset_autoincrement(options={})
      options[:to] ||= 1
      case self.connection.adapter_name
        when 'PostgreSQL'
          self.connection.execute "ALTER SEQUENCE #{self.table_name}_id_seq RESTART WITH #{options[:to]};"
        else
      end
    end
  end
end
