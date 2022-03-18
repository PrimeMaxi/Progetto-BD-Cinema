package sample.service.Implementations;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
  public List<ORARI> getOrario(LocalDate from, LocalDate to, Integer idFilm, Integer idSala){
    final var fromDate = Date.valueOf(from);
    final var toDate = Date.valueOf(to);
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    var proiezioni = proiezioneDAO.queryRangeProiezioni(fromDate,toDate,idFilm,idSala);
    var orari = proiezioni.stream().map(Proiezione::getOrari).collect(Collectors.toList());
    return ORARI.getListORARI().stream().filter(src->!orari.contains(src)).collect(Collectors.toList());
  }

}
