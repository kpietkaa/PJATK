class UsersController < ApplicationController
  before_action :set_user, only: [:show, :edit, :update, :destroy]

  def index
    @users = User.search(params[:term]).to_json
    render json: @users
    fresh_when @users
  end

  def show
  end

  def new
    @user = User.new
    render json: @user
  end

  def edit
  end

  def create
    @user = User.new(user_params)
    if @user.save
      render json: @user
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  def update
    if @user.update(user_params)
      render json: @user
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @user.destroy
    respond_to do |format|
      format.html { redirect_to users_url, notice: 'User was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    def set_user
      @user = User.find(params[:id])
      render json: @user
      fresh_when @user, last_modified: @user.updated_at
    end

    def user_params
      params.require(:user).permit(:login, :password, :phone_number, :birth_date)
    end
end
