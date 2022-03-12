package sample.models.dao.implDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import sample.models.dao.interfaceDAO.PostoDao;
import sample.models.entity.Posto;

public class PostoDaoImpl implements PostoDao {

  private static final String sqlRetrivePosto = "SELECT * FROM POSTO";
  private static final String sqlRetrivePostiByIdSala="select * from posto where idsalafk=?";

  private PreparedStatement queryRetrivePosto;
  private PreparedStatement queryRetrivePostiByIdSala;

  public PostoDaoImpl(Connection connection){
    try {
      queryRetrivePosto = connection.prepareStatement(sqlRetrivePosto);
      queryRetrivePostiByIdSala = connection.prepareStatement(sqlRetrivePostiByIdSala);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Posto> queryRetrivePosto(){
    var postoList = new ArrayList<Posto>();
    try {
      var rs = queryRetrivePosto.executeQuery();
      while(rs.next()){
        postoList.add(new Posto(
            rs.getInt(1), //IdPosto
            rs.getInt(3), //PostoY
            rs.getString(2).charAt(0), //PostoX
            rs.getInt(4)      //idSala
        ));
      }
      return postoList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public List<Posto> queryRetrivePostiByIdSala(Integer id){
    var postoList = new ArrayList<Posto>();
    try {
      queryRetrivePostiByIdSala.setInt(1,id);
      var rs = queryRetrivePostiByIdSala.executeQuery();
      while(rs.next()){
        postoList.add(new Posto(
            rs.getInt(1), //IdPosto
            rs.getInt(3), //PostoY
            rs.getString(2).charAt(0), //PostoX
            rs.getInt(4)      //idSala
        ));
      }
      return postoList;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"Errore: " + e.getMessage());
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

}
