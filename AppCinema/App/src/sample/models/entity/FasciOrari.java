package sample.models.entity;

public class FasciOrari {

  private String fascioOrario;
  private Integer count;

  public FasciOrari(String fascioOrario, Integer count) {
    this.fascioOrario = fascioOrario;
    this.count = count;
  }

  public String getFascioOrario() {
    return fascioOrario;
  }

  public void setFascioOrario(String fascioOrario) {
    this.fascioOrario = fascioOrario;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
