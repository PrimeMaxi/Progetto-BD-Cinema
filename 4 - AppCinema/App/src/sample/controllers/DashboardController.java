package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sample.Application;
import sample.controllers.Items.PaneDetailsCinemaController;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;
import sample.models.entity.Sala;
import sample.service.ActionListener;
import sample.service.SceneCreator;

public class DashboardController implements Initializable {

  public Label nomeCinema;
  public ImageView modificaCinema;
  public HBox loadCinema;
  public Pane dashboardPane;
  public Pane paneLeftDetails;
  private CinemaDAO cinemaDAO;
  private ActionListener actionListener;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeCinema();
    tastoModifica();
    loadDashboardCharts();
  }
  /**
   * Bottone Cinema
   * mostra le proiezioni nelle sale in data odierna
   * **/
  public void buttonCinemaAction(ActionEvent actionEvent) {
    paneLeftDetails.getChildren().clear();
    dashboardPane.getChildren().clear();
    try {
      final var pane = FXMLLoader.load(
          Objects.requireNonNull(Application.class.getResource("viewsRefactor/CinemaDashboard.fxml")));
      dashboardPane.getChildren().add((Node) pane);
      var paneCinemaDetails = FXMLLoader.load(
          Objects.requireNonNull(Application.class.getResource("viewsRefactor/TastoCinema/paneDetailsCinema.fxml")));
      paneLeftDetails.getChildren().add((Node) paneCinemaDetails);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Bottone TICKET
   * mostra la sala con i posti disponibile per generare il ticket
   * **/
  public void buttonTicketAction(ActionEvent actionEvent) {
    paneLeftDetails.getChildren().clear();
    dashboardPane.getChildren().clear();
    try {
      final var pane = FXMLLoader.load(
          Objects.requireNonNull(Application.class.getResource("viewsRefactor/Ticket.fxml")));
      dashboardPane.getChildren().add((Node) pane);
      var paneCinemaDetails = FXMLLoader.load(
          Objects.requireNonNull(Application.class.getResource("viewsRefactor/Booking.fxml")));
      paneLeftDetails.getChildren().add((Node) paneCinemaDetails);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * MAIN DASHBOARD
   * carica la dashboard con i charts al primo accesso
   * **/
  private void loadDashboardCharts(){
    try {
      final var pane = FXMLLoader.load(Objects.requireNonNull(
          Application.class.getResource("viewsRefactor/ChartsDashboard.fxml")));
      dashboardPane.getChildren().add((Node) pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Bottone DASHBOARD
   * mostra la dashboard principale con i charts, come al primo accesso
   * **/
  public void buttonDashboardAction(ActionEvent actionEvent) {
    dashboardPane.getChildren().clear();
    paneLeftDetails.getChildren().clear();
    try {
      final var pane = FXMLLoader.load(
          Objects.requireNonNull(Application.class.getResource("viewsRefactor/ChartsDashboard.fxml")));
      dashboardPane.getChildren().add((Node) pane);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initializeCinema(){
    try{
      cinemaDAO = new CinemaDAOImpl(DatabaseConnection.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Cinema cinema = cinemaDAO != null ? cinemaDAO.retriveCinema() : null;
    nomeCinema.setText(cinema != null ? cinema.getNomeCinema() : null);
  }
  private void tastoModifica(){
    modificaCinema.addEventHandler(
        MouseEvent.MOUSE_CLICKED,
        (EventHandler<Event>) event -> {
          System.out.println("Tasto modifica premuto");
          try {
            SceneCreator.launchScene("views/modificaCinema.fxml");
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
  }
}
