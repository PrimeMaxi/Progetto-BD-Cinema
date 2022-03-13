package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.BigliettoDao;
import sample.models.entity.Biglietto;

public class BigliettoDaoImpl implements BigliettoDao {
  private static final String sqlInsertBiglietto = "INSERT INTO BIGLIETTO(IdProiezioneFk,IdPostoFk) VALUES (?,?)";

  private PreparedStatement queryInsertBiglietto;

  public BigliettoDaoImpl(Connection connection){
      try {
          queryInsertBiglietto = connection.prepareStatement(sqlInsertBiglietto);
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

  @Override
  public void queryInsertBiglietto(List<Biglietto> biglietti){
      try {
          for(Biglietto biglietto : biglietti){
              queryInsertBiglietto.setInt(1,biglietto.getIdProiezione());
              queryInsertBiglietto.setInt(2,biglietto.getIdPosto());
              queryInsertBiglietto.execute();
          }
      System.out.println("Biglietto acquistato");
      } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
          e.printStackTrace();
      }
  }
}
