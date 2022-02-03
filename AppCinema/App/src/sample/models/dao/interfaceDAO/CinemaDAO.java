package sample.models.dao.interfaceDAO;

import sample.models.entity.Cinema;

public interface CinemaDAO {
  public Cinema retriveCinema();
  public void updateCinema(Cinema cinema);
}
