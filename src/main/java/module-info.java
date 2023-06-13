module game.snake_ladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.snake_ladder to javafx.fxml;
    exports game.snake_ladder;
}