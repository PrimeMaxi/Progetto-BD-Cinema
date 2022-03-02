package sample.models.entity;

public class Ricavi {

  private String titoloFilm;
  private Float incassi;

  public Ricavi(){}
  public Ricavi(String titolo, Float ricavi) {
    this.titoloFilm = titolo;
    this.incassi = ricavi;
  }

  public String getTitoloFilm() {
    return titoloFilm;
  }

  public void setTitoloFilm(String titolo) {
    this.titoloFilm = titolo;
  }

  public Float getIncassi() {
    return incassi;
  }

  public void setIncassi(Float incassi) {
    this.incassi = incassi;
  }
}
