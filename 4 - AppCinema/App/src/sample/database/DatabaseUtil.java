package sample.database;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DatabaseUtil {
  public static boolean checkLocalDate(java.util.Date min, java.util.Date max){
    var currentDate = Date.valueOf(LocalDate.now());
    return currentDate.after(min) && currentDate.before(max);
  }

  public static Date getCurrentDate(){
    return Date.valueOf(LocalDate.now());
  }

  public static Date getDatePlusDays(Integer day){
    var today = new java.util.Date();
    Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
    return Date.valueOf(tomorrow.toLocalDate());
  }

  public static Date getDateYesterday(){
    return Date.valueOf(LocalDate.now().minusDays(1));
  }
}
