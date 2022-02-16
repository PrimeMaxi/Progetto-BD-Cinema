package sample.models.entity;

import java.util.Date;
import java.util.List;
import javafx.scene.layout.BackgroundImage;
import sample.models.enumerations.ORARI;

public class Proiezione {
  private String idProiezione;
  private ORARI orarioProiezione;
  private Date inizioData;
  private Date fineData;
  private Sala sala;
  private List<Biglietto> bigliettoList;
  private Film film;
}
