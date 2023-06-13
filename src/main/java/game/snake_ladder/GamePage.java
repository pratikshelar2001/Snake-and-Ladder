package game.snake_ladder;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.io.IOException;

public class GamePage {
    public AnchorPane root;
    GamePage() throws IOException {
        root =  FXMLLoader.load(getClass().getResource("GamePage.fxml"));
//        directed to play game
//        root =  FXMLLoader.load(getClass().getResource("PlayArea.fxml"));

    }
}
