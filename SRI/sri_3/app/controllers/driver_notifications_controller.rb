class DriverNotificationsController < ApplicationController
  protect_from_forgery with: :null_session

  def create
    Event.create!(event_type: :driver, event: values)
    render json: { message: 'Driver informed.',
                   exceeded_values: values }
  end

  private

  def values
    params[:values].as_json
  end
end
