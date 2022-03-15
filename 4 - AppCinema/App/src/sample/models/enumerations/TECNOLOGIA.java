package sample.models.enumerations;

import java.util.List;

public enum TECNOLOGIA {
  DEFAULT("null"),
  IMAX("IMAX"),
  ISense("ISense"),
  ScreenX("ScrrenX"),
  Tridimensionale("3D");

  private final String tecnologia;

  TECNOLOGIA(String s) {
    tecnologia=s;
  }
  @Override
  public String toString() {
    return tecnologia;
  }
}
