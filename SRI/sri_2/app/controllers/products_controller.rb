class ProductsController < ApplicationController
  protect_from_forgery with: :null_session
  before_action :set_product, only: [:update, :destroy]

  def index
    @products = Product.all
    render json: @products.to_json
  end

  def show
    render json: Product.find(params[:id]).to_json
  end

  def create
    Product.transaction do
      @product = Product.new(product_params)

      if @product.save
        render json: @product.to_json
      end
    end
  end

  def update
    Product.transaction do
      if @product.update(product_params)
        render json: @product.to_json
      end
    end
  end

  def destroy
    Product.transaction do
      @product.destroy
      render json: { message: 'Product was successfully destroyed.' }.to_json
    end
  end

  private
    def set_product
      @product = Product.find(params[:id])
    end

    def product_params
      params.permit(:name, :category, :stock)
    end
end
