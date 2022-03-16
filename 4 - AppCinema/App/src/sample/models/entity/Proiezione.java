package sample.models.entity;

import java.util.Date;
import java.util.List;
import javafx.scene.layout.BackgroundImage;
import sample.models.enumerations.ORARI;

public class Proiezione {
  private Integer idProiezione;
  private String orarioProiezione;
  private Date inizioData;
  private Date fineData;
  private Sala sala;
  private List<Biglietto> bigliettoList;
  private Film film;
  private Integer prezzo;

  public Proiezione(Integer idProiezione, String orarioProiezione, Date inizioData,
      Date fineData, Integer idfilm, String titolo,Integer prezzo, Integer idSala ) {
    this.idProiezione = idProiezione;
    this.orarioProiezione = orarioProiezione;
    this.inizioData = inizioData;
    this.fineData = fineData;
    this.sala = new Sala(idSala);
    this.film = new Film();
    film.setIdFilm(idfilm);
    this.prezzo=prezzo;
    film.setTitolo(titolo);
  }

  public Integer getIdProiezione() {
    return idProiezione;
  }

  public void setIdProiezione(Integer idProiezione) {
    this.idProiezione = idProiezione;
  }

  public String getOrarioProiezione() {
    return orarioProiezione;
  }

  public void setOrarioProiezione(String orarioProiezione) {
    this.orarioProiezione = orarioProiezione;
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

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public List<Biglietto> getBigliettoList() {
    return bigliettoList;
  }

  public void setBigliettoList(List<Biglietto> bigliettoList) {
    this.bigliettoList = bigliettoList;
  }

  public Film getFilm() {
    return film;
  }

  public void setFilm(Film film) {
    this.film = film;
  }

  public Integer getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(Integer prezzo) {
    this.prezzo = prezzo;
  }

  @Override
  public String toString() {
    return "Proiezione{" +
        "idProiezione=" + idProiezione +
        ", orarioProiezione='" + orarioProiezione + '\'' +
        ", inizioData=" + inizioData +
        ", fineData=" + fineData +
        ", sala=" + sala.getIdSala() +
        ", film=" + film.getTitolo() +
        ", prezzo=" + prezzo +
        "}\n";
  }
}
