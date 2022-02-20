package sample.controllers.Items;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ItemSala2Controller implements Initializable  {

  public Label numeroSala;
  public AnchorPane paneItemSala;

  public void setNumeroSalaITem(Integer numeroSalaItem) {
    numeroSala.setText(numeroSalaItem.toString());
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

}
