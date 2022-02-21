package sample.models.entity;

public class Posto {
  private Integer idPosto;
  private Integer postoY;
  private Character filaX;
  private boolean disponibile;
  private Sala sala;

  public Posto(Integer idPosto, Integer postoY, Character filaX, boolean disponibile,
      Integer idSala) {
    this.idPosto = idPosto;
    this.postoY = postoY;
    this.filaX = filaX;
    this.disponibile = disponibile;
    this.sala = new Sala(idSala);
  }

  public Posto(Integer postoY, char filaX, boolean disponibile) {
    this.postoY = postoY;
    this.filaX = filaX;
    this.disponibile = disponibile;
  }

  public Integer getIdPosto() {
    return idPosto;
  }

  public void setIdPosto(Integer idPosto) {
    this.idPosto = idPosto;
  }

  public Integer getPostoY() {
    return postoY;
  }

  public void setPostoY(Integer postoY) {
    this.postoY = postoY;
  }

  public Character getFilaX() {
    return filaX;
  }

  public void setFilaX(Character filaX) {
    this.filaX = filaX;
  }

  public boolean isDisponibile() {
    return disponibile;
  }

  public void setDisponibile(boolean disponibile) {
    this.disponibile = disponibile;
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }
}
