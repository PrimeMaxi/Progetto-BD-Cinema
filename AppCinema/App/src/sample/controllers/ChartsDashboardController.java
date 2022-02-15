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
import sample.models.enumerations.ORARI;

public class ChartsDashboardController implements Initializable {

  public PieChart pieChart;
  public TableView moreIncomeTable;
  public LineChart lineChart;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setPieChart();
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
    TableColumn titoloFilm = new TableColumn("Titolo");
    TableColumn incassi = new TableColumn("Incassi");
    moreIncomeTable.getColumns().addAll(titoloFilm,incassi);
  }


}
