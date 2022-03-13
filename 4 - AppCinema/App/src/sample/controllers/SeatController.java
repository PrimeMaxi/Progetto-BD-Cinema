package sample.controllers;

import com.jfoenix.controls.JFXTogglePane;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SeatController extends TicketController {

  public Text numberSeat;
  public JFXTogglePane seatItem;
  private Character fila;
  private Integer posto;

  public void buttonClickedSeat(MouseEvent mouseEvent) {
    seatItem.setStyle("-fx-background-color:#00ac00");
  }

  public void setNumberSeat(Integer numberSeat) {
    this.numberSeat.setText(numberSeat.toString());
  }

  public void setNumberSeat(Character fila){
    this.numberSeat.setText(fila.toString());
    this.seatItem.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void setFila(Character fila) {
    this.fila = fila;
  }

  public void setPosto(Integer posto) {
    this.posto = posto;
  }
}
