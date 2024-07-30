import re
import json

DETAILED_MODE = True

STOCK_LOG_PATTERN = r'(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}) \| (.*)'
STOCK_FILENAME = "logs/stocks.log"
DETAILED_STATS_FILENAME = "stock_stats.txt"
DURATION_STATS_FILENAME = "duration_stats.txt"

def parse_line(log_pattern, line):
    match = re.match(log_pattern, line)
    if match:
        timestamp = match.group(1)
        duration = match.group(2)
        return {'timestamp': timestamp, 'duration': duration}
    else:
        Exception("Incorrect logging pattern")

def parse_log_file(log_pattern, filename):
    with open(filename, "r") as file:
        parsed_logs = []
        for line in file:
            parsed_logs.append(parse_line(log_pattern, line))

        return parsed_logs #in format {'timestamp': 'YYYY-MM-DD HH:MM:SS', 'duration': '...'}

def print_stock_stats(stockPerformance):
    with open(DETAILED_STATS_FILENAME, "w") as stats_txt:
        for stock in stockPerformance:
            print(stock, file=stats_txt)

def print_duration_stats(duration_map):
    with open(DURATION_STATS_FILENAME, 'w') as file:
        json.dump(duration_map, file, indent=4)

def sort_durations_by_range(milli_values):
    duration_map = {
        '0-250ms' : 0,
        '251-400ms' : 0,
        '401-600ms' : 0,
        '600+ms' : 0
    }
    for milli in milli_values:
        if milli <= 250: duration_map['0-250ms'] += 1
        elif milli <= 400: duration_map['251-400ms'] += 1
        elif milli <= 600: duration_map['401-600ms'] += 1
        else: duration_map['600+ms'] += 1
    return duration_map    

def value_to_millis(stocks):
    milli_values = []
    for stock in stocks:
        isNumber = re.match(r"(\d+)", stock.get('duration'))
        if isNumber:
            millis = int(isNumber.group(1))
            milli_values.append(millis)
    return milli_values      

def parse_and_print_logs():
    data = parse_log_file(STOCK_LOG_PATTERN, STOCK_FILENAME)
    if(DETAILED_MODE): print_stock_stats(data)
    print_duration_stats(sort_durations_by_range(value_to_millis(data)))

parse_and_print_logs()    
