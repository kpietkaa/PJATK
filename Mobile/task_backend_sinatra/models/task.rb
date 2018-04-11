class Task < ActiveRecord::Base
  validates :name, :price, :quantity, presence: true
  validates :price, :quantity, numericality: { only_integer: true }
end
