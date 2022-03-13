package sample.controllers;

import com.jfoenix.controls.JFXTogglePane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FilaItemController {

  public JFXTogglePane filaItem;
  public Text filaSeat;

  public void buttonClickedFila(MouseEvent mouseEvent) {
  }

  public void setFilaSeat(Character filaSeat) {
    this.filaSeat.setText(filaSeat.toString());
  }
}
