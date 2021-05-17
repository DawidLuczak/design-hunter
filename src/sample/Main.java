package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root =
            FXMLLoader.load(
                Objects.requireNonNull(
                    getClass().getResource(
                        "sample.fxml")
                )
            );
        primaryStage.setTitle(
            "Design Hunter"
        );
        primaryStage.setScene(
            new Scene(
                root,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width / 5 * 4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 5 * 4),
                true,
                SceneAntialiasing.BALANCED
            )
        );
        primaryStage.show();
        primaryStage.setResizable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
