# BUGS
# 1. Wybiera puste pole kiedy jest ono wybrane
#
# TODO
# 1. Wybranie innego pola niz na poczatku z 2 dostepnych
#
module PuzSim
  class Simulation
    def initialize(number)
      @number = number
    end

    def show_one_empty_gate
      loop do
        @gate = randomize
        if @gates[@gate].zero?
          @gates[@gate] = 99
          break
        end
      end
      puts "Empty gate: #{@gate}"
      puts "GATES: #{@gates}"
    end

    def change_to_other_close_gate
      @gates.each_index.select { |val| @gates[val] < 2 }
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
        puts "Choosen_gate: #{choosen_gate}"
        sim.show_one_empty_gate
        available_gates = sim.change_to_other_close_gate
        puts "Available gates: #{available_gates}"
        puts '########################################################'
        # check_if_win(choosen_gate)
        # write result of one simulation to file
      end
    end

    def not_change_first_decission
      sim = Simulation.new(@number)
      sim.validations
      @number.times do |_num|
        sim.put_ferrari_in_gate
        choosen_gate = sim.randomize
        puts "Choosen_gate: #{choosen_gate}"
        puts "Check if win: #{sim.check_if_win(choosen_gate)}"
        puts '########################################################'
      end
    end
  end
end
