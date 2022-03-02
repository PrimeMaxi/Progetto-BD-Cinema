package sample.models.enumerations;

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
}
