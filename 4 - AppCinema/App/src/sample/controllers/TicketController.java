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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.PostoDaoImpl;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.PostoDao;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;
import sample.service.Implementations.DefaultTicketService;
import sample.service.TicketService;

public class TicketController implements Initializable {

  public GridPane gridPosti;
  private PostoDao postoDao;
  private AnchorPane seatItem;
  private TicketService ticketService = new DefaultTicketService();


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setGridPosti();
  }
  public void setGridPosti(){
    postoDao = new PostoDaoImpl(DatabaseConnection.getConnection());
    final var postiList = postoDao.queryRetrivePosto();

  }
}
