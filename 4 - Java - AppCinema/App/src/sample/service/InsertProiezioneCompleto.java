package sample.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.ChoiceBox;
import sample.models.entity.Proiezione;
import sample.models.enumerations.ORARI;


public interface InsertProiezioneCompleto {

  void setNumeroSala(ChoiceBox<Integer> numeroSala);

  void setChoichFilms(ChoiceBox<String> films);

  List<ORARI> getOrario(LocalDate from, LocalDate to, Integer idFilm, Integer idSala);

  HashSet<Integer> getSaleDisponibili(LocalDate from, LocalDate to);

  List<ORARI> getOrariDisponibili(Set<Integer> saleDisponibili, Integer idSala);
}
