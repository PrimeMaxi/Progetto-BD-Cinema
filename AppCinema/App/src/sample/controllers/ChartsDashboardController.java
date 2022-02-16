package sample.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.ChartDAOImpl;
import sample.models.entity.FasciOrari;
import sample.models.entity.Ricavi;
import sample.models.enumerations.ORARI;

public class ChartsDashboardController implements Initializable {

  public PieChart pieChart;
  public TableView<Ricavi> moreIncomeTable;
  public LineChart lineChart;
  public TableColumn<Ricavi,String> titoloFilm;
  public TableColumn<Ricavi,Float> incassi;
  private ChartDAOImpl chartDAO;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setPieChart();
    setMoreIncomeTable();
  }

  private void setPieChart(){
    chartDAO = new ChartDAOImpl(DatabaseConnection.getConnection());
    var list = chartDAO.queryFasciOrari();
    var fascio16 = list.stream().filter(src -> Objects.equals(src.getFascioOrario(), "16-18")).findFirst();
    var fascio18 = list.stream().filter(src -> Objects.equals(src.getFascioOrario(), "18-20")).findFirst();
    var fascio20 = list.stream().filter(src -> Objects.equals(src.getFascioOrario(), "20-22")).findFirst();
    var fascio22 = list.stream().filter(src -> Objects.equals(src.getFascioOrario(), "22-24")).findFirst();

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data(ORARI.FASCIA_16_18.toString(),fascio16.isPresent() ? fascio16.get().getCount() : 0),
        new PieChart.Data(ORARI.FASCIA_18_20.toString(),fascio18.isPresent() ? fascio18.get().getCount() : 0),
        new PieChart.Data(ORARI.FASCIA_20_22.toString(),fascio20.isPresent() ? fascio20.get().getCount() : 0),
        new PieChart.Data(ORARI.FASCIA_22_24.toString(),fascio22.isPresent() ? fascio22.get().getCount() : 0)
    );
    pieChart.setData(pieChartData);
    pieChart.setTitle("FASCI ORARI");
  }

  private void setMoreIncomeTable(){
    moreIncomeTable.getColumns().clear();
    titoloFilm.setCellValueFactory(new PropertyValueFactory<>("titoloFilm"));
    incassi.setCellValueFactory(new PropertyValueFactory<>("incassi"));
    chartDAO = new ChartDAOImpl(DatabaseConnection.getConnection());
    final var data = FXCollections.observableList(chartDAO.queryRicavi());
    moreIncomeTable.getColumns().addAll(titoloFilm,incassi);
    moreIncomeTable.setItems(data);
  }


}
