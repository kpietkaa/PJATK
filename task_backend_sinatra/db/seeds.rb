Task.delete_all
Task.reset_autoincrement

puts 'Creating sample tasks'
['Milk', 'Bread', 'Butter'].each do |task|
  Task.find_or_create_by(
    name: task,
    price: Random.rand(1..100),
    quantity: Random.rand(1..10)
  )
end
