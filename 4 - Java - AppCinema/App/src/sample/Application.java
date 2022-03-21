package sample;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    static Parent root;
    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        primaryStage = stage;
        try {
            root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("views/Main.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Application.root = root;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        Application.primaryStage = primaryStage;
    }
}
