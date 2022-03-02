package sample.controllers;

import java.net.URL;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;

public class BookingController implements Initializable {

  public ChoiceBox<String> listFilmTicket;
  public ChoiceBox<String> listOrarioTicket;
  public DatePicker dataTicket;
  public Button buttonCerca;
  private TicketDao ticketDao;
  private List<Ticket> ticketList;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    ticketDao = new TicketDaoImpl(DatabaseConnection.getConnection());
    ticketList = ticketDao.queryTicket();
    dataTicket.setChronology(Chronology.ofLocale(Locale.ITALIAN));
    var filmList = ticketList.stream().map(Ticket::getTitolo).collect(Collectors.toList());
    listFilmTicket.setItems(FXCollections.observableList(filmList));
    var orariList = ticketList.stream().map(Ticket::getOrarioProiezione).collect(Collectors.toList());
    listOrarioTicket.setItems(FXCollections.observableList(orariList));

  }

  public void choiceDataTicket(ActionEvent actionEvent) {

  }

  public void acquistaTicket(ActionEvent actionEvent) {
  }

  public void buttonCerca(ActionEvent actionEvent) {

  }
}
