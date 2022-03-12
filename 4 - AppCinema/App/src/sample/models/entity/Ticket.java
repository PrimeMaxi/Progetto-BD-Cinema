package sample.models.entity;

public class Ticket {

  private Integer idProiezione;
  private String orarioProiezione;
  private Integer idFilm;
  private String titolo;
  private Integer IdSalaFk;

  public Ticket(Integer idProiezione, String orarioProiezione, Integer idFilm,
      String titolo, Integer idSalaFk) {
    this.idProiezione = idProiezione;
    this.orarioProiezione = orarioProiezione;
    this.idFilm = idFilm;
    this.titolo = titolo;
    IdSalaFk = idSalaFk;
  }

  public Ticket(Integer idProiezione, String orarioProiezione, Integer idFilm,
      String titolo) {
    this.idProiezione = idProiezione;
    this.orarioProiezione = orarioProiezione;
    this.idFilm = idFilm;
    this.titolo = titolo;
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

  @Override
  public String toString() {
    return "Ticket{" +
        "idProiezione=" + idProiezione +
        ", orarioProiezione='" + orarioProiezione + '\'' +
        ", idFilm=" + idFilm +
        ", titolo='" + titolo + '\'' +
        '}';
  }

  public void setIdSalaFk(Integer idSalaFk) {
    IdSalaFk = idSalaFk;
  }

  public Integer getIdSalaFk() {
    return IdSalaFk;
  }
}
