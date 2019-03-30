package ru.indraft.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class HolidayService {

    private static final String CSV_PATH = "/calendar/proizv_cal.csv";

    private static class Column {
        private final static int YEAR = 0;
    }

    private List<CSVRecord> rows;

    HolidayService() throws IOException {
        var in = new FileReader(getClass().getResource(CSV_PATH).getFile());
        var parser = CSVFormat.EXCEL.withSkipHeaderRecord().parse(in);
        rows = parser.getRecords();
    }

    boolean checkDateIsHoliday(LocalDate date) {
        List<Integer> holidays = getHolidays(date);
        var day = date.getDayOfMonth();
        return holidays.contains(day);
    }

    private List<Integer> getHolidays(LocalDate date) {
        String holidaysStr = getHolidaysOfMonthAndYear(date);
        String[] holidaysArr = holidaysStr.split(",");
        return Arrays.stream(holidaysArr).filter(item -> !item.endsWith("*")).map(Integer::valueOf).collect(Collectors.toList());
    }

    private String getHolidaysOfMonthAndYear(LocalDate date) {
        var year = date.getYear();
        var month = date.getMonth().getValue();
        for (CSVRecord record : rows) {
            if (String.valueOf(year).equals(record.get(Column.YEAR))) {
                return record.get(month);
            }
        }
        return "0";
    }

}
