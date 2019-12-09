package kapesystems.crmsprint3isi.utils;

import java.util.Comparator;

public class Date implements Comparable {

    private static String EMPTY_FIELD = "00";
    private static String SEPARATOR = "/";

    private String day, month, year;

    public Date() {
        this.day = EMPTY_FIELD;
        this.month = EMPTY_FIELD;
        this.year = EMPTY_FIELD;
    }

    public Date(String date) throws ArrayIndexOutOfBoundsException {
        String[] datePieces = date.split(SEPARATOR);

        this.day = datePieces[0];
        this.month = datePieces[1];
        this.year = datePieces[2];
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return day + SEPARATOR + month + SEPARATOR + year;
    }

    @Override
    public int compareTo(Object o) {
        Date that = (Date) o;

        if(Integer.parseInt(this.year) != Integer.parseInt(that.year)) {
            return (Integer.compare(Integer.parseInt(this.year), Integer.parseInt(that.year)));
        }
        else if(Integer.parseInt(this.month) != Integer.parseInt(that.month)) {
            return (Integer.compare(Integer.parseInt(this.month), Integer.parseInt(that.month)));
        }
        else {
            return (Integer.compare(Integer.parseInt(this.day), Integer.parseInt(that.day)));
        }
    }
}
