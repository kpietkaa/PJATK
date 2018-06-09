class PitStopsController < ApplicationController
  protect_from_forgery with: :null_session

  def index
    render json: { result: CheckDecisionService.new.call }
  end

  def create
    RedisJob::Pusher.push_to_notifications({ param: 1 }, HeadOfRace)
    render json: { message: 'Request sent.' }
  end

  private

  def values
    params[:values].as_json
  end
end
