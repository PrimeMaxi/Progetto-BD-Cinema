package sample.service.Implementations;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Ticket;
import sample.service.BookingService;

public class DefaultBookingService implements BookingService {

  @Override
  public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
    return java.sql.Date.valueOf(dateToConvert);
  }

  /**
   * Selezione Data, Film e orario proiezione
   * **/
  public void setChoicheBoxListFilmOrari(DatePicker dataTicket, Date dataProiezione, List<Ticket> ticketList, ChoiceBox<String> listFilmTicket, ChoiceBox<String> listOrarioTicket,
      TicketDao ticketDao) {
    //Restituisce la lista di proiezioni per la data selezionata
    ticketList = ticketDao.queryTicket(dataProiezione);
    //Creo una lista di Titoli
    var filmList = ticketList.stream().map(Ticket::getTitolo).collect(Collectors.toList());
    //Imposto i titoli al CoicheBox dei film
    listFilmTicket.setItems(FXCollections.observableList(filmList));
    List<Ticket> finalTicketList = ticketList;
//    Map<Integer,Integer>
    listFilmTicket.setOnAction((actionEvent -> {
      //Film selezionato
      String selectedFilm = listFilmTicket.getSelectionModel().getSelectedItem();
      //Lista di Ticket per il film selezionato
      var selectedOrario = finalTicketList.stream().filter(src->src.getTitolo().equals(selectedFilm)).collect(
          Collectors.toList());
      listOrarioTicket.setItems(FXCollections.observableList(selectedOrario.stream().map(Ticket::getOrarioProiezione).collect(
          Collectors.toList())));
    }));
  }
  /**
   * Selezione Data, Film e orario proiezione (Data corrente - Default)
   * **/
  public void setChoicheBoxListFilmOrari(DatePicker dataTicket,List<Ticket> ticketList, ChoiceBox<String> listFilmTicket, ChoiceBox<String> listOrarioTicket,
      TicketDao ticketDao) {
    dataTicket.setChronology(Chronology.ofLocale(Locale.ITALIAN));
    ticketList = ticketDao.queryTicket();
    var filmList = ticketList.stream().map(Ticket::getTitolo).collect(Collectors.toList());
    listFilmTicket.setItems(FXCollections.observableList(filmList));
    List<Ticket> finalTicketList = ticketList;
    listFilmTicket.setOnAction((actionEvent -> {
      String selectedFilm = listFilmTicket.getSelectionModel().getSelectedItem();
      var selectedOrario = finalTicketList.stream().filter(src->src.getTitolo().equals(selectedFilm)).collect(
          Collectors.toList());
      listOrarioTicket.setItems(FXCollections.observableList(selectedOrario.stream().map(Ticket::getOrarioProiezione).collect(
          Collectors.toList())));
    }));
  }
}
