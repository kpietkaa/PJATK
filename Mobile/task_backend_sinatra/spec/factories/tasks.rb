FactoryBot.define do
  factory :task do
    name Faker::Food.ingredient
    price Random.rand(1..100)
    quantity Random.rand(1..10)
  end
end
