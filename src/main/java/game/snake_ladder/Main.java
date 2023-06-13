package game.snake_ladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Group root;

    @Override
    public void start(Stage stage) throws IOException {
        root = new Group();


        Image image = new Image(getClass().getResourceAsStream("snakeladdername.png"));
        stage.getIcons().add(image);

        stage.setTitle("Snake & Ladder");
        GamePage page = new GamePage();
        root.getChildren().add(page.root);
        stage.setScene(new Scene(root,1080,720));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}