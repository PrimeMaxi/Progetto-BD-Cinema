package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import sample.models.dao.interfaceDAO.FilmDAO;

public class FilmDaoImpl implements FilmDAO {

  private Connection connection;
  private PreparedStatement insertFilm;

  @Override
  public void insertFilm(String titolo, String trama, String regia, String anno, String durata,
      String genere) {

  }

  @Override
  public boolean deleteFilm() {
    return false;
  }
}
