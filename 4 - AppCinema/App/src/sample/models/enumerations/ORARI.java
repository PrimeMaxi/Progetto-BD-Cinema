package sample.models.enumerations;

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

  public List<ORARI> getListORARI(){
    return new ArrayList<>(Arrays.asList(ORARI.values()));
  }
}
