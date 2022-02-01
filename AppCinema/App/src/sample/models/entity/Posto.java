package sample.models.entity;

public class Posto {
  private String idPosto;
  private Integer postoY;
  private char filaX;
  private boolean disponibile;
  private Sala sala;

  public Posto(Integer postoY, char filaX, boolean disponibile) {
    this.postoY = postoY;
    this.filaX = filaX;
    this.disponibile = disponibile;
  }

  public String getIdPosto() {
    return idPosto;
  }

  public void setIdPosto(String idPosto) {
    this.idPosto = idPosto;
  }

  public Integer getPostoY() {
    return postoY;
  }

  public void setPostoY(Integer postoY) {
    this.postoY = postoY;
  }

  public char getFilaX() {
    return filaX;
  }

  public void setFilaX(char filaX) {
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
