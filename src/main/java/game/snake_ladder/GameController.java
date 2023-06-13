package game.snake_ladder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class GameController {
    @FXML
    public void startgame(MouseEvent event) throws IOException {
        AnchorPane start = FXMLLoader.load(getClass().getResource("PlayArea.fxml"));
        Main.root.getChildren().setAll(start);
    }
}