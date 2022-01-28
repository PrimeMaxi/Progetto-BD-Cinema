package sample.models.entity;

import java.time.Year;
import java.util.Date;
import sample.models.enumerations.GENERE;

public class Film {

  private String IdFilm;
  private String titolo;
  private String trama;
  private String regia;
  private Year annoUscita;
  private String durataFilm;
  private String genere;
  private String recensione;

  public Film(){}
  public Film(String titolo, String trama, String regia, Year annoUscita, String durataFilm,
      String genere) {
    this.titolo = titolo;
    this.trama = trama;
    this.regia = regia;
    this.annoUscita = annoUscita;
    this.durataFilm = durataFilm;
    this.genere = genere;
  }

  public String getIdFilm() {
    return IdFilm;
  }

  public void setIdFilm(String idFilm) {
    IdFilm = idFilm;
  }

  public String getTitolo() {
    return titolo;
  }

  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  public String getTrama() {
    return trama;
  }

  public void setTrama(String trama) {
    this.trama = trama;
  }

  public String getRegia() {
    return regia;
  }

  public void setRegia(String regia) {
    this.regia = regia;
  }

  public Year getAnnoUscita() {
    return annoUscita;
  }

  public void setAnnoUscita(Year annoUscita) {
    this.annoUscita = annoUscita;
  }

  public String getDurataFilm() {
    return durataFilm;
  }

  public void setDurataFilm(String durataFilm) {
    this.durataFilm = durataFilm;
  }

  public String getGenere() {
    return genere;
  }

  public void setGenere(String genere) {
    this.genere = genere;
  }

  public String getRecensione() {
    return recensione;
  }

  public void setRecensione(String recensione) {
    this.recensione = recensione;
  }
}
