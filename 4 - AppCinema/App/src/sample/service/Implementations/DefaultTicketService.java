package sample.service.Implementations;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sample.controllers.SeatController;

public class DefaultTicketService implements sample.service.TicketService {

  @Override
  public SeatController getSeatController(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
      AnchorPane anchorPane = fxmlLoader.load();
      return fxmlLoader.getController();
    } catch (IOException e) {
      e.printStackTrace();
    }
    throw new NullPointerException("Errore getSeatController returns null");
  }

}
