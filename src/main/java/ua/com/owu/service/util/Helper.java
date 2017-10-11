package ua.com.owu.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

public class Helper {
    public Date dateFormater(final String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.parse(date);


    }

    public String phoneFormater(String phoneNum) {
        if (phoneNum.length() < 10) {
            System.out.println("error!");
        }
        String newNumber = phoneNum.replaceAll("([-)(+\\s])", "");
        return newNumber.substring(newNumber.length() - 10, newNumber.length() - 1);
    }

    public HashSet<String> tagsFormater(String tags) {
        return new HashSet<>(Arrays.asList(tags.replace(" ", "").split(",")));
    }

    public Double priceCounter(Integer fullPrice, Integer discount) {
        if (discount == 0) return (double) fullPrice;
        else return (double) fullPrice - ((double) fullPrice / 100 * (double) discount);
    }
}
