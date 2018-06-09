class PitStopsController < ApplicationController
  protect_from_forgery with: :null_session

  def create
    Event.create!(event_type: :pit_stop, event: values)
    render json: { message: 'Pit-stop informed.',
                   exceeded_values: values }
  end

  private

  def values
    params[:values].as_json
  end
end
