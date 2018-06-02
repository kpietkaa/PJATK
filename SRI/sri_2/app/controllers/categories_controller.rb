class CategoriesController < ApplicationController
  protect_from_forgery with: :null_session
  before_action :set_category, only: [:update, :destroy]

  def index
    @categories = singleton_category.all
    render json: @categories.to_json
  end

  def show
    render json: singleton_category.all.to_json
  end

  def create
    Category.transaction do
      @category = Category.new(category_params)

      if @category.save
        singleton_category.fetch_from_db
        render json: @category.to_json
      end
    end
  end

  def update
    Category.transaction do
      if @category.update(category_params)
        singleton_category.fetch_from_db
        render json: @category.to_json
      end
    end
  end

  def destroy
    Category.transaction do
      @category.destroy
      singleton_category.fetch_from_db
      render json: { message: 'Category was successfully destroyed.' }.to_json
    end
  end

  private
  def set_category
    @category = Category.find(params[:id])
  end

  def category_params
    params.permit(:name, :parent_category_id)
  end

  def singleton_category
    Rails.application.config.categories
  end
end
