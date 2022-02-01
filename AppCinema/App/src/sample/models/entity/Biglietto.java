package sample.models.entity;

public class Biglietto {
  private String idBiglietto;
  private Float prezzo;
  private Proiezione proiezione;

  public Biglietto(Float prezzo) {
    this.prezzo = prezzo;
  }

  public String getIdBiglietto() {
    return idBiglietto;
  }

  public void setIdBiglietto(String idBiglietto) {
    this.idBiglietto = idBiglietto;
  }

  public Float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(Float prezzo) {
    this.prezzo = prezzo;
  }

  public Proiezione getProiezione() {
    return proiezione;
  }

  public void setProiezione(Proiezione proiezione) {
    this.proiezione = proiezione;
  }
}
