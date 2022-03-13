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

  public void buttonClickedSeat(MouseEvent mouseEvent) {

  }

  public void setNumberSeat(Integer numberSeat) {
    this.numberSeat.setText(numberSeat.toString());
  }

  public void setNumberSeat(Character fila){
    this.numberSeat.setText(fila.toString());
    this.seatItem.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
  }
}
