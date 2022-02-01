package sample.models.entity;

import java.util.List;
import sample.models.enumerations.AUDIO;
import sample.models.enumerations.TECNOLOGIA;

public class Sala {
  private String idSala;
  private Integer capienza;
  private TECNOLOGIA tecnologia;
  private AUDIO audio;
  private Cinema cinema;
  private List<Posto> postoList;
  private List<Proiezione> proiezioneList;

  public Sala(Integer capienza, TECNOLOGIA tecnologia, AUDIO audio,
      Cinema cinema) {
    this.capienza = capienza;
    this.tecnologia = tecnologia;
    this.audio = audio;
    this.cinema = cinema;
  }

  public String getIdSala() {
    return idSala;
  }

  public void setIdSala(String idSala) {
    this.idSala = idSala;
  }

  public Integer getCapienza() {
    return capienza;
  }

  public void setCapienza(Integer capienza) {
    this.capienza = capienza;
  }

  public TECNOLOGIA getTecnologia() {
    return tecnologia;
  }

  public void setTecnologia(TECNOLOGIA tecnologia) {
    this.tecnologia = tecnologia;
  }

  public AUDIO getAudio() {
    return audio;
  }

  public void setAudio(AUDIO audio) {
    this.audio = audio;
  }

  public Cinema getCinema() {
    return cinema;
  }

  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  public List<Posto> getPostoList() {
    return postoList;
  }

  public void setPostoList(List<Posto> postoList) {
    this.postoList = postoList;
  }

  public List<Proiezione> getProiezioneList() {
    return proiezioneList;
  }

  public void setProiezioneList(List<Proiezione> proiezioneList) {
    this.proiezioneList = proiezioneList;
  }
}
