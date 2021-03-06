package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;
import sample.service.BookingService;
import sample.service.Implementations.DefaultBookingService;


public class BookingController extends TicketController implements Initializable {

  public ChoiceBox<String> listFilmTicket;
  public ChoiceBox<String> listOrarioTicket;
  public DatePicker dataTicket;
  public Button buttonCerca;
  private TicketDao ticketDao;
  private List<Ticket> ticketList;
  private TicketController ticketController;
  private final BookingService bookingService = new DefaultBookingService();
  private DashboardController dashboardController;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    dataTicket.setValue(LocalDate.now());
    ticketDao = new TicketDaoImpl(DatabaseConnection.getConnection());
    bookingService.setChoicheBoxListFilmOrari(dataTicket,ticketList,listFilmTicket,listOrarioTicket, ticketDao);
  }

  public void choiceDataTicket(ActionEvent actionEvent) {
    var dataProiezione = bookingService.convertToDateViaSqlDate(dataTicket.getValue());
    bookingService.setChoicheBoxListFilmOrari(dataTicket,dataProiezione,ticketList,listFilmTicket,listOrarioTicket, ticketDao);
    System.out.println("Data selezionata: " + dataProiezione.toString());
  }

  public void acquistaTicket(ActionEvent actionEvent) {

    var alert = new Alert(AlertType.CONFIRMATION, "Confermi di acquistare i biglietti selezionati ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    alert.showAndWait();
    if (alert.getResult() == ButtonType.YES) {
      ticketController.insertBiglietto();
      ticketController.setGridPosti();
    }
  }

  public void buttonCerca(ActionEvent actionEvent) {
    if(listFilmTicket.getSelectionModel().isEmpty() || listOrarioTicket.getSelectionModel().isEmpty()){
      JOptionPane.showMessageDialog(null,"Errore: non hai selezionato il film o l'orario");
    } else {
      var data = bookingService.convertToDateViaSqlDate(dataTicket.getValue());
      var ticket =
          ticketDao.queryTicketOne(data, listFilmTicket.getValue(), listOrarioTicket.getValue());
      ticketController.setTicket(ticket);
    }
  }

  public void setTicketController(TicketController ticketController) {
    this.ticketController = ticketController;
  }
}
