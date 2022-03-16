package sample.database;

import java.sql.Date;
import java.time.LocalDate;

public class DatabaseUtil {
  public static boolean checkLocalDate(java.util.Date min, java.util.Date max){
    var currentDate = Date.valueOf(LocalDate.now());
    return currentDate.after(min) && currentDate.before(max);
  }
}
