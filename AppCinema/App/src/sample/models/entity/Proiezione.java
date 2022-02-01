package sample.models.entity;

import java.util.Date;
import java.util.List;
import javafx.scene.layout.BackgroundImage;

public class Proiezione {
  private String idProiezione;
  private String[] orariProiezioni;
  private Date inizioData;
  private Date fineData;
  private Sala sala;
  private List<Biglietto> bigliettoList;
  private Film film;
}
