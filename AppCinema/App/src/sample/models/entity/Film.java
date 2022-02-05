package sample.models.entity;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.Year;
import sample.models.enumerations.GENERE;

public class Film {

  private final static String PATTERNDATE = "dd-MM-yyyy";
  private Integer idFilm;
  private String titolo, trama = "DEFAULT", regia;
  private Year annoUscita;
  private Time durataFilm;
  private GENERE genere;
  private Date inizioData, fineData;


  public Film(){}
  public Film(String titolo, String trama, String regia, Year annoUscita, Time durataFilm,
      GENERE genere) {
    this.titolo = titolo;
    this.trama = trama;
    this.regia = regia;
    this.annoUscita = annoUscita;
    this.durataFilm = durataFilm;
    this.genere = genere;
  }

  public Integer getIdFilm() {
    return idFilm;
  }

  public void setIdFilm(Integer idFilm) {
    this.idFilm = idFilm;
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

  public Time getDurataFilm() {
    return durataFilm;
  }

  public void setDurataFilm(Time durataFilm) {
    this.durataFilm = durataFilm;
  }

  public GENERE getGenere() {
    return genere;
  }

  public void setGenere(GENERE genere) {
    this.genere = genere;
  }

  public Date getInizioData() {
    return inizioData;
  }

  public void setInizioData(Date inizioData) {
    this.inizioData = inizioData;
  }

  public Date getFineData() {
    return fineData;
  }

  public void setFineData(Date fineData) {
    this.fineData = fineData;
  }
}
