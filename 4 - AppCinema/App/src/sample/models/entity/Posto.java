package sample.models.entity;

public class Posto {
  private Integer idPosto;
  private Integer postoY;
  private Character filaX;
  private Sala sala;

  public Posto(Integer idPosto, Integer postoY, Character filaX,
      Integer idSala) {
    this.idPosto = idPosto;
    this.postoY = postoY;
    this.filaX = filaX;
    this.sala = new Sala(idSala);
  }

  public Posto(Integer postoY, char filaX) {
    this.postoY = postoY;
    this.filaX = filaX;
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

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }
}
