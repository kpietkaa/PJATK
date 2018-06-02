class CreateProducts < ActiveRecord::Migration
  def change
    create_table :products do |t|
      t.string :name
      t.references :category
      t.integer :stock

      t.timestamps null: false
    end
  end
end
