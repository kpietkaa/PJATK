class UsersController < ApplicationController
  include WashOut::SOAP
  soap_service namespace: 'urn:sri_4'

  before_action :set_user, only: [:show, :edit, :update, :destroy]

  soap_action 'index',
              args: nil,
              return: :string
  def index
    @users = User.all
    users = @users.map{ |user| { id: user.id, name: user.name, surname: user.surname } }
    render soap: users.to_s
  end

  soap_action 'show',
              args: { id: :integer },
              return: :string
  def show
    if @user.present?
      render soap: { name: @user.name, surname: @user.surname }.to_s
    else
      render soap: 'User not found'
    end
  end

  soap_action 'create',
              args: { name: :string, surname: :string },
              return: :string
  def create
    @user = User.new(user_params)
    if @user.save
      render soap: 'OK'
    else
      render soap: @user.errors.to_s
    end
  end

  soap_action 'update',
              args: { id: :integer, name: :string, surname: :string },
              return: :string
  def update
    if @user.update(user_params)
      render soap: 'OK'
    else
      render soap: @user.errors.to_s
    end
  end

  soap_action 'destroy',
              args: { id: :integer },
              return: :string
  def destroy
    @user.destroy
    render soap: 'Destroyed'
  end

  private

  def set_user
    @user = User.find(params[:id])
  rescue ActiveRecord::RecordNotFound
    @user = nil
  end

  def user_params
    params.permit('id', 'name', 'surname')
  end
end
