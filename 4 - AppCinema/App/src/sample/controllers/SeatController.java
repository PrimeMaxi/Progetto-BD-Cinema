package sample.controllers;

import com.jfoenix.controls.JFXTogglePane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SeatController extends TicketController {

  public Text numberSeat;
  public JFXTogglePane seatItem;
  private Character fila;
  private Integer posto;

  public void buttonClickedSeat(MouseEvent mouseEvent) {
    if (!seatItem.isDisabled()) {
      seatItem.setStyle("-fx-background-color:#00ac00");
    }
  }

  public void setNumberSeat(Integer numberSeat) {
    this.numberSeat.setText(numberSeat.toString());
  }

  public void setOccupiedSeat(){
    seatItem.setStyle("-fx-background-color: #ff0000");
    seatItem.setDisable(true);
    System.out.println("Posto occupato");
  }

  public void setFila(Character fila) {
    this.fila = fila;
  }

  public void setPosto(Integer posto) {
    this.posto = posto;
  }
}
