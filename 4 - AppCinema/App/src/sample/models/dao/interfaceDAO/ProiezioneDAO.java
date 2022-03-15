package sample.models.dao.interfaceDAO;

import java.util.List;
import sample.models.entity.Proiezione;
import sample.models.enumerations.ORARI;

public interface ProiezioneDAO {

  List<Proiezione> queryListProiezioniFilm();

  boolean queryUpdateProiezione(Integer idProiezione, Integer prezzo, ORARI orari);
}
