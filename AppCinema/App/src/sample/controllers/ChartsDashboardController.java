package sample.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.ChartDAOImpl;
import sample.models.dao.interfaceDAO.ChartDAO;
import sample.models.entity.Ricavi;
import sample.models.entity.SalaAmount;
import sample.models.enumerations.ORARI;

public class ChartsDashboardController implements Initializable {

  public PieChart pieChart;
  public TableView<Ricavi> moreIncomeTable;

  public TableColumn<Ricavi,String> titoloFilm;
  public TableColumn<Ricavi,Float> incassi;
  public BarChart<String,Integer> barChart;
  public CategoryAxis sale;
  public NumberAxis affluenza;
  private ChartDAO chartDAO;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setPieChart();
    setMoreIncomeTable();
    setBarChart();
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
    pieChart.setLabelLineLength(10);
    pieChart.setLegendSide(Side.LEFT);
  }

  private void setBarChart(){
    chartDAO = new ChartDAOImpl(DatabaseConnection.getConnection());
    var listMaxAffluenza = chartDAO.queryChartSalaOrari();
    var listSala = chartDAO.queryAmountSala();

    for(SalaAmount idSala : listSala){
      var series = new XYChart.Series<String,Integer>();
      series.setName(String.format("SALA %d",idSala.getIdSala()));
      for(ORARI fasciaOrario : ORARI.values()){
        var data = listMaxAffluenza.stream().filter(src -> Objects.equals(src.getFasciaOraria(), fasciaOrario.toString())).findFirst();
        series.getData().add(new XYChart.Data<>(fasciaOrario.toString(),data.isPresent() ? data.get().getSumAffluenza() : 0));
      }
      barChart.getData().add(series);
    }
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
