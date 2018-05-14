class PostsController < ApplicationController
  include WashOut::SOAP
  soap_service namespace: 'urn:sri_4'

  before_action :set_post, only: [:show, :edit, :update, :destroy]

  soap_action 'index',
              args: nil,
              return: :string
  def index
    @posts = Post.all
    posts = @posts.map{ |post| display_post(post) }
    render soap: posts.to_s
  end

  soap_action 'show',
              args: { id: :integer },
              return: :string
  def show
    if @post.present?
      render soap: display_post(@post).to_s
    else
      render soap: 'Post not found'
    end
  end

  soap_action 'create',
              args: { title: :string, body: :string, 'user_id' => :integer },
              return: :string
  def create
    @post = Post.new(post_params)

    if @post.save
      render soap: 'OK'
    else
      render soap: @post.errors.to_s
    end
  end

  # client.call(:update, message: {id: 1, 'user_id' => 1} )
  soap_action 'update',
              args: { id: :integer, title: :string, body: :string, 'user_id' => :integer },
              return: :string
  def update
    if @post.update(post_params)
      render soap: 'OK'
    else
      render soap: @post.errors.to_s
    end
  end

  soap_action 'destroy',
              args: { id: :integer },
              return: :string
  def destroy
    @post.destroy
    render soap: 'Destroyed'
  end

  private

  def set_post
    @post = Post.find(params[:id])
  rescue ActiveRecord::RecordNotFound
    @post = nil
  end

  def post_params
    params.permit('id', 'title', 'body', 'user_id')
  end

  def full_name(id)
    user = User.find(id)
    [user.name, user.surname].compact.join(' ')
  rescue ActiveRecord::RecordNotFound
    ''
  end

  def display_post(post)
    { id: post.id, title: post.title, body: post.body, user: full_name(post.user_id) }
  end
end
