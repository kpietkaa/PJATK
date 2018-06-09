class HeadOfRace
  include Sidekiq::Worker

  def perform
    DecisionService.new.call
  end
end
