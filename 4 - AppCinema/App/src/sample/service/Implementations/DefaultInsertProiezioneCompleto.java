package sample.service.Implementations;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.implDAO.SalaDAOImpl;
import sample.models.dao.implDAO.TicketDaoImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.dao.interfaceDAO.SalaDAO;
import sample.models.dao.interfaceDAO.TicketDao;
import sample.models.entity.Film;
import sample.models.entity.Proiezione;
import sample.models.entity.Sala;
import sample.models.enumerations.ORARI;

public class DefaultInsertProiezioneCompleto implements sample.service.InsertProiezioneCompleto {

  private SalaDAO salaDAO;
  private FilmDAO filmDAO;
  private List<Proiezione> proiezioni;
  private ProiezioneDAO proiezioneDAO;

  @Override
  public void setNumeroSala(ChoiceBox<Integer> numeroSala){
    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    final var amountSala = salaDAO.queryRetriveSala().stream().map(Sala::getIdSala).collect(Collectors.toList());
    numeroSala.setItems(FXCollections.observableList(amountSala));
    if(!amountSala.isEmpty())
      numeroSala.setValue(amountSala.get(0));
  }

  @Override
  public void setChoichFilms(ChoiceBox<String> films) {
    filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
    final var filmsTitolo =
        filmDAO.queryListFilm().stream().map(Film::getTitolo).collect(Collectors.toList());
    films.setItems(FXCollections.observableList(filmsTitolo));
    if(!filmsTitolo.isEmpty())
      films.setValue(filmsTitolo.get(0));
  }

  @Override
  public List<ORARI> getOrario(LocalDate from, LocalDate to,Integer idFilm, Integer idSala){
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    final var fromDate = Date.valueOf(from);
    final var toDate = Date.valueOf(to);
    var proiezioni = proiezioneDAO.queryRangeProiezioni(fromDate,toDate,idFilm,idSala);
    var orari = proiezioni.stream().map(Proiezione::getOrari).collect(Collectors.toList());
    return ORARI.getListORARI().stream().filter(src->!orari.contains(src)).collect(Collectors.toList());
  }

  private List<Proiezione> getProiezioniRange(LocalDate from, LocalDate to){
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    return proiezioneDAO.queryRangeProiezioni(Date.valueOf(from),Date.valueOf(to),null,null);
  }

  /** Sale disponibili se c'è almeno una fascia oraria disponibile* */
  @Override
  public HashSet<Integer> getSaleDisponibili(LocalDate from, LocalDate to) {
    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    proiezioni = getProiezioniRange(from,to);
    final var sale = salaDAO.queryRetriveSala();
    //Sale che non hanno proiezione
    salaDAO = new SalaDAOImpl(DatabaseConnection.getConnection());
    final var saleDisponibili = salaDAO.queryRetriveSala().stream().map(Sala::getIdSala).filter(
        idSala -> !proiezioni.stream().map(i -> i.getSala().getIdSala())
            .collect(Collectors.toList()).contains(
                idSala)
    ).collect(Collectors.toCollection(HashSet::new));
    System.out.print(saleDisponibili);
    //Sale che hanno proiezione ma non in tutte le fasce orarie
    for (Sala sala : sale){
      final var count = proiezioni.stream().filter(src->src.getSala().getIdSala() == sala.getIdSala()).count();
      if(count<4){
        saleDisponibili.add(sala.getIdSala());
      }
    }
    System.out.print(saleDisponibili);

    return saleDisponibili;
  }

  @Override
  public List<ORARI> getOrariDisponibili(Set<Integer> saleDisponibili, Integer idSala){
    final var sale = saleDisponibili.stream().filter(i->i==idSala).collect(Collectors.toSet());
    proiezioni = proiezioni.stream().filter(i->sale.contains(i.getSala().getIdSala())).collect(Collectors.toList());
    return ORARI.getListORARI().stream().filter(
        src->!proiezioni.stream().map(Proiezione::getOrari).collect(Collectors.toList()).contains(src)
    ).collect(Collectors.toList());
  }

}
