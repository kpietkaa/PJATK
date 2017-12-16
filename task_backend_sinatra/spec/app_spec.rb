require_relative 'spec_helper'
require_relative '../app'

RSpec.describe App, type: :request do
  let!(:task1) { create :task }
  let!(:task2) { create :task }
  let!(:task3) { create :task }

  describe 'GET /tasks' do
    subject(:request_response) do
      get 'tasks'
    end

    it { expect(subject.status).to eq 200 }

    describe 'returned tasks' do
      subject(:response_tasks) { JSON.parse(request_response.body) }
      it { expect(response_tasks.length).to eq 3 }
    end
  end

  describe 'GET /tasks/:id' do
    subject(:request_response) do
      get "/tasks/#{task1.id}"
    end

    subject(:response_task) { JSON.parse(request_response.body) }
    it 'get proper values', :aggregate_failures do
      expect(response_task['id']).to eq task1.id
      expect(response_task['name']).to eq task1.name
      expect(response_task['price']).to eq task1.price
      expect(response_task['quantity']).to eq task1.quantity
    end
  end
end
