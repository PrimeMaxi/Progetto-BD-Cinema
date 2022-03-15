package sample.controllers.Items;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PaneDetailsCinemaController implements Initializable {

  public AnchorPane paneDetailsCinema;
  public Pane paneLeftDetails;
  public Label salaNumeroDetailsCinema;
  public Label totNumero;
  public Label dispNumero;
  public Label tecnInfo;
  public Label audioInfo;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void buttonModificaDetailsCinema(ActionEvent actionEvent) {
  }

  public void setSalaNumeroDetailsCinema(Integer numero) {
    salaNumeroDetailsCinema.setText(numero.toString());
  }

  public void setTotNumero(Label totNumero) {
    this.totNumero = totNumero;
  }

  public void setDispNumero(Label dispNumero) {
    this.dispNumero = dispNumero;
  }

  public void setTecnInfo(Label tecnInfo) {
    this.tecnInfo = tecnInfo;
  }

  public void setAudioInfo(Label audioInfo) {
    this.audioInfo = audioInfo;
  }
}
