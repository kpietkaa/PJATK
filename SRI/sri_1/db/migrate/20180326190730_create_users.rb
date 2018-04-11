class CreateUsers < ActiveRecord::Migration[5.1]
  def change
    create_table :users do |t|
      t.string :login
      t.string :password
      t.string :phone_number
      t.string :birth_date

      t.timestamps
    end
  end
end
