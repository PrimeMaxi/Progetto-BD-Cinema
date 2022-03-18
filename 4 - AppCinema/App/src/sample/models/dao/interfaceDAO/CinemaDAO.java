package sample.models.dao.interfaceDAO;

import sample.models.entity.Cinema;

public interface CinemaDAO {

  boolean updatePlusNumeroSala();

  public Cinema retriveCinema();
  public void updateCinema(Cinema cinema);
}
