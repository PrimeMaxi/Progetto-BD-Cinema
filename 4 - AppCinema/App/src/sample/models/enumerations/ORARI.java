package sample.models.enumerations;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ORARI {
  FASCIA_16_18("16-18"),
  FASCIA_18_20("18-20"),
  FASCIA_20_22("20-22"),
  FASCIA_22_24("22-24");

  private String fascia;

  ORARI(String s) {
    fascia=s;
  }

  @Override
  public String toString() {
    return fascia;
  }

  public static List<ORARI> getListORARI(){
    return new ArrayList<>(Arrays.asList(ORARI.values()));
  }

  public static ORARI getORARI(String orario){
    return Arrays.stream(ORARI.values()).filter(src->src.toString().equals(orario)).findFirst().orElse(null);
  }

  public static Time[] getTime(ORARI orari){
    if(orari.toString().equals("16-18")){
      var time = new Time[2];
      time[0] = Time.valueOf(LocalTime.of(16, 0, 0));
      time[1] = Time.valueOf(LocalTime.of(18, 0, 0));
      return time;
    }
    if(orari.toString().equals("18-20")){
      var time = new Time[2];
      time[0] = Time.valueOf(LocalTime.of(18, 0, 0));
      time[1] = Time.valueOf(LocalTime.of(20, 0, 0));
      return time;    }
    if(orari.toString().equals("20-22")){
      var time = new Time[2];
      time[0] = Time.valueOf(LocalTime.of(20, 0, 0));
      time[1] = Time.valueOf(LocalTime.of(22, 0, 0));
      return time;    }
    if(orari.toString().equals("22-24")){
      var time = new Time[2];
      time[0] = Time.valueOf(LocalTime.of(22, 0, 0));
      time[1] = Time.valueOf(LocalTime.of(24, 0, 0));
      return time;    }
    return null;
  }
}
