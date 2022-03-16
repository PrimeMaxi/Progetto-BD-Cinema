package sample.models.dao.interfaceDAO;

import java.sql.Time;
import java.util.List;
import sample.models.entity.Proiezione;
import sample.models.enumerations.ORARI;

public interface ProiezioneDAO {

  List<Proiezione> queryListProiezioniFilm();

  boolean queryUpdateProiezione(Integer idProiezione, Integer prezzo, ORARI orari);

  boolean queryInsertProiezione(Time oraInizio, Time oraFine, ORARI orari, Integer prezzo,
      Integer idFilmFk, Integer idSalaFk);
}
