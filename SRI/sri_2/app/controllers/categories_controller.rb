class CategoriesController < ApplicationController
  protect_from_forgery with: :null_session
  before_action :set_category, only: [:update, :destroy]

  def index
    @categories = Category.all
    render json: @categories.to_json
  end

  def show
    render json: Category.find(params[:id]).to_json
  end

  def create
    Category.transaction do
      @category = Category.new(category_params)

      if @category.save
        render json: @category.to_json
      end
    end
  end

  def update
    Category.transaction do
      if @category.update(category_params)
        render json: @category.to_json
      end
    end
  end

  def destroy
    Category.transaction do
      @category.destroy
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
end
