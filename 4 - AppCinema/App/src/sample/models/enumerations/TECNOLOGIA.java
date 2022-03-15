package sample.models.enumerations;

import java.util.Arrays;
import java.util.List;

public enum TECNOLOGIA {
  DEFAULT("null"),
  IMAX("IMAX"),
  ISense("ISense"),
  ScreenX("ScreenX"),
  Tridimensionale("3D");

  private final String tecnologia;

  TECNOLOGIA(String s) {
    tecnologia=s;
  }
  @Override
  public String toString() {
    return tecnologia;
  }

  public static TECNOLOGIA getEnumByString(String tecnologia){
    return Arrays.stream(TECNOLOGIA.values()).filter(src->src.toString().equals(tecnologia)).findFirst().orElse(null);
  }
}
