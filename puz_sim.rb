module PuzSim
  class Simulation
    def initialize(number)
      @number = number
    end

    def show_one_empty_gate(choosen_gate)
      temp_gates = @gates
      temp_gates[choosen_gate] = 99
      temp_gates.each_index.select{ |idx| temp_gates[idx] == 0 }.first
    end

    def change_to_other_close_gate(choosen_gate, empty_gate)
      temp_gates = @gates
      temp_gates[choosen_gate] = 99
      temp_gates[empty_gate] = 99
      temp_gates.each_index.select{ |idx| temp_gates[idx] != 99 }.first
    end

    def put_ferrari_in_gate
      @gates = [0, 0, 0]
      @gates[randomize] = 1
      puts "put_ferrari_in_gate | @gates: #{@gates}"
    end

    def check_if_win(gate)
      return 1 if @gates[gate] == 1
      0
    end

    def randomize
      Random.rand(0..2)
    end

    def validations
      raise 'Number of simulations not seted' if @number.nil?
    end
  end

  class << self
    def number_of_simulations(number)
      @number = number
      self
    end

    def change_first_decission
      sim = Simulation.new(@number)
      sim.validations
      @number.times do |_num|
        sim.put_ferrari_in_gate
        choosen_gate = sim.randomize
        empty_gate = sim.show_one_empty_gate(choosen_gate)
        choosen_gate = sim.change_to_other_close_gate(choosen_gate, empty_gate)
      end
    end

    def not_change_first_decission
      sim = Simulation.new(@number)
      sim.validations
      @number.times do |_num|
        sim.put_ferrari_in_gate
        choosen_gate = sim.randomize
      end
    end
  end
end
