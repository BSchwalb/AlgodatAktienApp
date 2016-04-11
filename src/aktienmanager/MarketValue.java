/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aktienmanager;

/**
 *
 * @author Link
 */
public class MarketValue {

    private final int day, month, year, open, high, low, close, adjClose;
    private final long volume;
    private static final IllegalArgumentException ARGUMENT_EXCEPTION = new IllegalArgumentException("String not convertable to MarketValue");

    public static MarketValue getInstance(String line) {
        String text[] = line.split(",");
        if (text.length != 7)
            throw ARGUMENT_EXCEPTION;
        for (int i = 0; i < text.length; i++) {
            text[i] = text[i].replaceAll("\\.", "");
        }
        String date[] = text[0].split("-");
        if (date.length != 3)
            throw ARGUMENT_EXCEPTION;

        return getInstance(date[0], date[1], date[2],
                           text[1], text[2], text[3], text[4], text[5], text[6]);
    }

    private static MarketValue getInstance(String... args) {
        return new MarketValue(
                Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Long.parseLong(args[7]), Integer.parseInt(args[8]));
    }

    public MarketValue(int year, int month, int day, int open, int high, int low, int close, long volume, int adjClose) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(year + "-" + month + "-" + day);
        addArgument(builder, getOpen()/100.0, getHigh()/100.0, getLow()/100.0, getClose()/100.0, getVolume(), getAdjClose()/100.0);
        return builder.toString();
    }

    private static StringBuilder addArgument(StringBuilder builder, Object... textList) {
        for (Object text : textList) {
            builder.append(",");
            builder.append(text);
        }
        return builder;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getOpen() {
        return open;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public int getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    public int getAdjClose() {
        return adjClose;
    }

}
