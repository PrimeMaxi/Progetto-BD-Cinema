package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.TicketDao;

public class BookingController implements Initializable {

  public ChoiceBox listFilmTicket;
  public ChoiceBox listOrarioTicket;
  private TicketDao ticketDao;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void choiceDataTicket(ActionEvent actionEvent) {
  }

  public void acquistaTicket(ActionEvent actionEvent) {
  }

  public void Ciao(){
    ticketDao = new TicketDaoImpl(DatabaseConnection.getConnection());
    var ticketList = ticketDao.queryTicket();
    ticketList.forEach(System.out::println);
  }
}
