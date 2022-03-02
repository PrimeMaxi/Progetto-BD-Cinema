package sample.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
  public static boolean isValidDate(String inDate) {
    if(inDate.length() != 4)
      return false;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(inDate.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }
  public static boolean isValidFilmLength(String inDate) {
    if(inDate.length() != 8)
      return false;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(inDate.trim());
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }
}
