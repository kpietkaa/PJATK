class AddEvent < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.integer :event_type
      t.json :event

      t.timestamps null: false
    end
  end
end
