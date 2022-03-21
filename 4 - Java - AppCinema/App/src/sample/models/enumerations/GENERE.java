package sample.models.enumerations;

import java.util.Arrays;

public enum GENERE {
  Azione("Azione"),
  Horror("Horror"),
  Fantascienza("Fantascienza"),
  Comico("Comico"),
  Thriller("Thriller"),
  Western("Western"),
  Documentario("Documentario"),
  Drammatico("Drammatico");

  private final String genere;

  GENERE(String s) {
    genere = s;
  }

  @Override
  public String toString() {
    return genere;
  }

  public static GENERE getEnumByString(String genere){
    return Arrays.stream(GENERE.values()).filter(src->src.toString().equals(genere)).findFirst().orElse(null);
  }
}
