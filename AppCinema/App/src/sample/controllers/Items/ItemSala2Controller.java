package sample.controllers.Items;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.models.entity.Sala;

public class ItemSala2Controller implements Initializable  {

  public Label numeroSala;
  public AnchorPane paneItemSala;
  private Sala sala;

  public void setNumeroSalaITem(Integer numeroSalaItem) {
    numeroSala.setText(numeroSalaItem.toString());
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    click();
  }
  public void click(){

  }

  public void setSala(Sala sala){
    this.sala=sala;
  }
}
