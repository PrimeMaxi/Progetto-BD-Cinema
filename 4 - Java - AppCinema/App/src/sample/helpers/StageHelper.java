package sample.helpers;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Application;

public class StageHelper {

  public static <T> T getControllerLoader(String fxml){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      final var path = String.format("viewsRefactor/%s.fxml",fxml);
      fxmlLoader.setLocation(Application.class.getResource(path));
      final var pane = fxmlLoader.load();
      return fxmlLoader.getController();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static FXMLLoader getLoaderFxml(String fxml){
      FXMLLoader fxmlLoader = new FXMLLoader();
      final var path = String.format("viewsRefactor/%s.fxml",fxml);
      fxmlLoader.setLocation(Application.class.getResource(path));
      return fxmlLoader;
  }

  public static void close(AnchorPane pane){
    var root = (Stage) pane.getScene().getWindow();
    root.close();
  }

  public static Time getTime(String hour, String minutes, String seconds){
    final var fomatter = hour+":"+minutes+":"+seconds;
    final var simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    try {
      final var date = simpleDateFormat.parse(fomatter).getTime();
      return new Time(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

}
