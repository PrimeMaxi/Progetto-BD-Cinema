package sample.controllers;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SeatController extends TicketController {

  public Text numberSeat;

  public void buttonClickedSeat(MouseEvent mouseEvent) {

  }

  public void setNumberSeat(Integer numberSeat) {
    this.numberSeat.setText(numberSeat.toString());
  }
}
