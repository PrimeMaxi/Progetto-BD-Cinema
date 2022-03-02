package sample.models.dao.interfaceDAO;

import java.util.List;
import sample.models.entity.FasciOrari;
import sample.models.entity.MaxAffluenza;
import sample.models.entity.Ricavi;
import sample.models.entity.SalaAmount;

public interface ChartDAO {

  List<Ricavi> queryRicavi();

  List<FasciOrari> queryFasciOrari();

  List<MaxAffluenza> queryChartSalaOrari();

  List<SalaAmount> queryAmountSala();
}
