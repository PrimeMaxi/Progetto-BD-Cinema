package sample.controllers;

import java.net.URL;
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
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data(ORARI.FASCIA_16_18.toString(),1),
        new PieChart.Data(ORARI.FASCIA_18_20.toString(),5),
        new PieChart.Data(ORARI.FASCIA_20_22.toString(),15),
        new PieChart.Data(ORARI.FASCIA_22_24.toString(),20)
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
