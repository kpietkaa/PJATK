puts 'Create user'
user = User.create!(name: 'Test', surname: 'Test')
puts 'Create post'
post = Post.create!(title: 'Test title', body: 'Body body body body', user_id: user.id)
puts 'Create comment'
Comment.create!(body: 'Comment comment comment comment', post_id: post.id, user_id: user.id)
