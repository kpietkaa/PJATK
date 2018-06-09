class HeadOfRacesController < ApplicationController
  protect_from_forgery with: :null_session

  def index
    render json: { request: DecisionService.new.call.as_json }
  end

  def create
    RedisJob::Pusher.push_to_decisions({params: decision}, 'CheckDecision')
    render json: { message: 'Decision sent.' }
  end

  private

  def decision
    params['decision']
  end
end
