package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;

public class FilmDaoImpl implements FilmDAO {

  private Connection connection;
  private PreparedStatement insertFilm, deleteFilm;

  public FilmDaoImpl(Connection connection) throws SQLException {
    this.connection=connection;
    insertFilm = connection.prepareStatement("INSERT INTO FILM (Titolo,Trama,Regia,Anno,Durata,Genere) VALUES (?,?,?,?,?,?)");
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
  public boolean deleteFilm() {
    return false;
  }
}
