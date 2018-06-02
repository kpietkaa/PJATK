puts 'Create Categories'
[
  [1000, 'Komputery i oprogramowanie', nil],
  [1001, 'Komputery', 1000],
  [1002, 'Stacjonarne', 1001],
  [1003, 'Laptopy', 1001],
  [1004, 'Inne komputery', 1001],
  [1005, 'Oprogramowanie', 1000],
  [1006, 'Systemy operacyjne', 1005],
  [1007, 'Programy typu office', 1005],
  [1008, 'Programy specjalistyczne', 1005],
  [1009, 'Programy antywirusowe', 1005]
].each do |category|
  Category.create!(id: category[0],
                   name: category[1],
                   parent_category_id: category[2])
end

puts 'Create Products'

[
  [2000, 'Dell Optiplex 3010', 1002, 3],
  [2001, 'Dell Optiplex 3020', 1002, 5],
  [2002, 'Dell XPS', 1003, 10],
  [2003, 'Dell Latitude', 1003, 1],
  [2004, 'MS Windows 8', 1006, 2],
  [2005, 'MS Windows 10', 1006, 0],
  [2006, 'MS Office 2015', 1007, 4]
].each do |product|
  Product.create!(id: product[0],
                  name: product[1],
                  category_id: product[2],
                  stock: product[3])
end
