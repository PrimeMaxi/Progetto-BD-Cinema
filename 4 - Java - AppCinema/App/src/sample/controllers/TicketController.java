package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.BigliettoDaoImpl;
import sample.models.dao.implDAO.PostoDaoImpl;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.BigliettoDao;
import sample.models.dao.interfaceDAO.PostoDao;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Biglietto;
import sample.models.entity.Posto;
import sample.models.entity.Ticket;
import sample.service.Implementations.DefaultTicketService;
import sample.service.TicketService;

public class TicketController implements Initializable {

  public static final String fila = "A";

  public GridPane gridPosti;
  private PostoDao postoDao;
  private TicketDao ticketDao;
  private BigliettoDao bigliettoDao;
  private AnchorPane seatItem;
  private TicketService ticketService = new DefaultTicketService();
  private Ticket ticket;
  private List<SeatController> listSeatControllers;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void setGridPosti(){
    postoDao = new PostoDaoImpl(DatabaseConnection.getConnection());
    ticketDao = new TicketDaoImpl(DatabaseConnection.getConnection());
    var listOccupiedSeats = ticketDao.queryListOccupiedSeats(ticket.getIdProiezione(),ticket.getOrarioProiezione(),ticket.getIdSalaFk());
    var sala = postoDao.queryRetrivePostiByIdSala(ticket.getIdSalaFk());
    var listFila = sala.stream().map(Posto::getFilaX).distinct().collect(Collectors.toList());
    listSeatControllers = new ArrayList<>();

    int column = 0;
    int row = 1;

    for(Character fila : listFila){
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(Application.class.getResource("viewsRefactor/FilaItem.fxml"));
      try {
        AnchorPane anchorPane = fxmlLoader.load();
        FilaItemController filaItemController = fxmlLoader.getController();
        filaItemController.setFilaSeat(fila);
        gridPosti.add(anchorPane,column,row++);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    column = 1;
    row = 1;

    for(Posto item : sala){
      try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("viewsRefactor/SeatItem.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        SeatController seatController = fxmlLoader.getController();
        seatController.setNumberSeat(item.getPostoY());
        seatController.setPosto(item.getPostoY());
        seatController.setFila(item.getFilaX());
        seatController.setIdPostoFk(item.getIdPosto());
        listSeatControllers.add(seatController);

        if(listOccupiedSeats.stream().anyMatch(src-> Objects.equals(src.getPosto(), item.getPostoY())
            && src.getFila().equals(item.getFilaX()) && ticket.getDataBiglietto().toLocalDate().equals(src.getDataBiglietto().toLocalDate()))){
          seatController.setOccupiedSeat();
        }

        if(column == 15){
          column=1;
          row++;
        }

        gridPosti.add(anchorPane,column++,row);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void insertBiglietto(){
    var bigliettiSelezionati = listSeatControllers.stream().filter(SeatController::getSelected).map(src-> new Biglietto(ticket.getIdProiezione(),src.getIdPostoFk())).collect(
        Collectors.toList());
    bigliettoDao = new BigliettoDaoImpl(DatabaseConnection.getConnection());
    if (!bigliettiSelezionati.isEmpty()) {
      bigliettoDao.queryInsertBiglietto(bigliettiSelezionati);
    }
    bigliettiSelezionati.clear();
    listSeatControllers.clear();
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
    System.out.println(ticket);
    setGridPosti();
  }
}
