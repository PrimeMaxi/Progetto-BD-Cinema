package sample.models.entity;

public class MaxAffluenza {

  private Integer sala;
  private String fasciaOraria;
  private Integer sumAffluenza;

  public MaxAffluenza(Integer sala, String fasciaOraria, Integer sumAffluenza) {
    this.sala = sala;
    this.fasciaOraria = fasciaOraria;
    this.sumAffluenza = sumAffluenza;
  }

  public Integer getSala() {
    return sala;
  }

  public void setSala(Integer sala) {
    this.sala = sala;
  }

  public String getFasciaOraria() {
    return fasciaOraria;
  }

  public void setFasciaOraria(String fasciaOraria) {
    this.fasciaOraria = fasciaOraria;
  }

  public Integer getSumAffluenza() {
    return sumAffluenza;
  }

  public void setSumAffluenza(Integer sumAffluenza) {
    this.sumAffluenza = sumAffluenza;
  }
}
