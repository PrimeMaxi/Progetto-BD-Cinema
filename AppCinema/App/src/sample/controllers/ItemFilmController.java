package sample.controllers;

import javafx.scene.control.Label;

public class ItemFilmController {

  public Label nomeFilmItem;

  public Label getNomeFilmItem() {
    return nomeFilmItem;
  }

  public void setNomeFilmItem(String nomeFilmItem) {
    this.nomeFilmItem.setText(nomeFilmItem);
  }
}
