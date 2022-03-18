package sample.models.dao.interfaceDAO;

import sample.models.entity.Cinema;

public interface CinemaDAO {

  boolean updatePlusNumeroSala();
  Cinema retriveCinema();
  void updateCinema(Cinema cinema);
}
