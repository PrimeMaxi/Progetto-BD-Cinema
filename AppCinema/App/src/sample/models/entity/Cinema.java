package sample.models.entity;

import java.util.List;

public class Cinema {

  private Integer IdCinema;
  private String NomeCinema, indirizzo, provincia, città;
  private Integer telefono, numeroSala;
  private List<Sala> salaList;

  public Cinema(){}
  public Cinema(String nomeCinema, String indirizzo, String provincia, Integer numeroSala,
      String città, Integer telefono) {
    NomeCinema = nomeCinema;
    this.indirizzo = indirizzo;
    this.provincia = provincia;
    this.numeroSala = numeroSala;
    this.città = città;
    this.telefono = telefono;
  }

  public Integer getIdCinema() {
    return IdCinema;
  }

  public void setIdCinema(Integer idCinema) {
    IdCinema = idCinema;
  }

  public String getNomeCinema() {
    return NomeCinema;
  }

  public void setNomeCinema(String nomeCinema) {
    NomeCinema = nomeCinema;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public Integer getNumeroSala() {
    return numeroSala;
  }

  public void setNumeroSala(Integer numeroSala) {
    this.numeroSala = numeroSala;
  }

  public String getCittà() {
    return città;
  }

  public void setCittà(String città) {
    this.città = città;
  }

  public Integer getTelefono() {
    return telefono;
  }

  public void setTelefono(Integer telefono) {
    this.telefono = telefono;
  }

  public List<Sala> getSalaList() {
    return salaList;
  }

  public void setSalaList(List<Sala> salaList) {
    this.salaList = salaList;
  }
}
