package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;

public class FilmDaoImpl implements FilmDAO {

  private Connection connection;
  private PreparedStatement insertFilm, deleteFilm, queryFilmById;

  public FilmDaoImpl(Connection connection) throws SQLException {
    this.connection=connection;
    insertFilm = connection.prepareStatement("INSERT INTO FILM (Titolo,Trama,Regia,Anno,Durata,Genere) VALUES (?,?,?,?,?,?)");
    deleteFilm = connection.prepareStatement("DELETE FROM FILM WHERE idfilm = ?");
    queryFilmById = connection.prepareStatement("SELECT idfilm FROM FILM WHERE titolo= ?");
  }

  @Override
  public void insertFilm(Film film) {
    try {
      insertFilm.setString(1,film.getTitolo());
      insertFilm.setString(2,film.getTrama().isEmpty() ? "DEFAULT" : film.getTrama());
      insertFilm.setString(3,film.getRegia());
      insertFilm.setInt(4,film.getAnnoUscita().getValue());
      insertFilm.setTime(5,film.getDurataFilm());
      insertFilm.setObject(6, film.getGenere(), Types.OTHER);
      System.out.println(insertFilm.executeUpdate());
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }
  }

  @Override
  public boolean deleteFilm(Integer id) {
    try {
      deleteFilm.setInt(1,id);
      return deleteFilm.executeUpdate() > 1;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }
    return false;
  }

  @Override
  public Integer queryFilmById(String titolo) {
    try {
      queryFilmById.setString(1,titolo);
      ResultSet resultQuery = queryFilmById.executeQuery();
      return resultQuery.getInt(1);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
    }
    return 0;
  }
}
