package sample.service;

import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import sample.models.enumerations.ORARI;


public interface InsertProiezioneCompleto {

  void setNumeroSala(ChoiceBox<Integer> numeroSala);

  void setChoichFilms(ChoiceBox<String> films);

  List<ORARI> getOrario(LocalDate from, LocalDate to, Integer idFilm, Integer idSala);
}
