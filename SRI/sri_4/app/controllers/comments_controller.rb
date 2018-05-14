class CommentsController < ApplicationController
  include WashOut::SOAP
  soap_service namespace: 'urn:sri_4'

  before_action :set_comment, only: [:show, :edit, :update, :destroy]

  soap_action 'index',
              args: nil,
              return: :string
  def index
    @comments = Comment.all
    comments = @comments.map{ |comment| display_comment(comment) }
    render soap: comments.to_s
  end

  soap_action 'show',
              args: { id: :integer },
              return: :string
  def show
    if @comment.present?
      render soap: display_comment(@comment).to_s
    else
      render soap: 'Comment not found'
    end
  end

  soap_action 'create',
              args: { body: :string, 'post_id' => :integer, 'user_id' => :integer },
              return: :string
  def create
    @comment = Comment.new(comment_params)

    if @comment.save
      render soap: 'OK'
    else
      render soap: @comment.errors.to_s
    end
  end

  # client.call(:update, message: {id: 1, body: "Not bad", 'post_id' => 1, 'user_id' => 1})
  soap_action 'update',
              args: { id: :integer, body: :string, 'post_id' => :integer, 'user_id' => :integer },
              return: :string
  def update
    if @comment.update(comment_params)
      render soap: 'OK'
    else
      render soap: @comment.errors.to_s
    end
  end

  soap_action 'destroy',
              args: { id: :integer },
              return: :string4
  def destroy
    @comment.destroy
    render soap: 'Destroyed'
  end

  private

  def set_comment
    @comment = Comment.find(params[:id])
  rescue ActiveRecord::RecordNotFound
    @comment = nil
  end

  def comment_params
    params.permit('body', 'post_id', 'user_id')
  end

  def display_comment(comment)
    { id: comment.id, body: comment.body, post_title: post(comment.post_id), user: full_name(comment.user_id) }
  end

  def full_name(id)
    user = User.find(id)
    [user.name, user.surname].compact.join(' ')
  rescue ActiveRecord::RecordNotFound
    ''
  end

  def post(id)
    Post.find(id).title
  rescue ActiveRecord::RecordNotFound
    ''
  end
end
