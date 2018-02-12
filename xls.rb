require 'spreadsheet'

class XLS
  def initialize(results)
    @results = results
    Spreadsheet.client_encoding = 'UTF-8'
    @spreadsheet = Spreadsheet::Workbook.new
    @sheet = @spreadsheet.create_worksheet
    @sheet.name = 'Simulation_results.xls'
    @columns = {
      nr:     { id: 0, width: 10 },
      result: { id: 1, wodth: 6 }
    }
    set_headers
    @current_row = 1
  end

  def call
    @results.each do |result|
      @row = {
        nr:     @current_row,
        result: result
      }
      @sheet.row(@current_row).push(*@row.sort_by { |key, _| @columns[key][:id] }.map { |_, value| value })
      @current_row += 1
    end
    set_formatting
    create_file
  end

  private

  def set_headers
    @columns.each_key { |key| @sheet.row(0).push(key.to_s.capitalize) }
  end

  def set_formatting
    @columns.values.each do |column|
      @sheet.column(column[:id]).width = column[:width]
    end
  end

  def create_file
    @spreadsheet.write @sheet.name
  end
end
