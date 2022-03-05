package sample.models.dao.interfaceDAO;

import java.util.List;
import sample.models.entity.Posto;

public interface PostoDao {

  List<Posto> queryRetrivePosto();

  List<Posto> queryRetrivePostiByIdSala(Integer id);
}
