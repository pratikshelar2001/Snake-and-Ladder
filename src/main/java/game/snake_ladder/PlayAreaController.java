package game.snake_ladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;
import java.util.Random;

public class PlayAreaController
{

    @FXML
        Text dice_no_op;
    @FXML
        Text changeturn;
    @FXML
    ImageView blue_position,red_position;


    int turn=1;
    HashMap<Pair<Double,Double>,Pair<Double,Double>> snake_ladder_coordinates;
    @FXML
    public void diceclicked(MouseEvent event) throws IOException
    {
        getSnakeLadderCoodinates();
        Random random = new Random(); //Random library in java
        int result = random.nextInt(6) + 1 ; // OP-> 0 to 5 +1-> 1 to 6
        //result =1; //Hardcode rolling
        dice_no_op.setText("Prev. Result --> Player " + turn + " got " + result);

        if(turn==1)
        {
            Pair new_position = movement(red_position.getTranslateX(),red_position.getTranslateY(),result);
            //getTranslateX -> return movement in  X position &vv.

            red_position.setTranslateX((Double) new_position.getKey()); // set new position of X
            red_position.setTranslateY((Double) new_position.getValue());
            checkwin(1,red_position.getTranslateX(), red_position.getTranslateY());
        }
        else
        {
            Pair new_position = movement(blue_position.getTranslateX(),blue_position.getTranslateY(),result);
            blue_position.setTranslateX((Double) new_position.getKey());
            blue_position.setTranslateY((Double) new_position.getValue());
            checkwin(2,blue_position.getTranslateX(), blue_position.getTranslateY());
        }



        if(turn == 1)
        {
            if(result!=6)
            {
                turn =2;
                changeturn.setText("Player 2's turn now !");


            }
        }
        else
        {
            if(result!=6)
            {
                turn =1;
                changeturn.setText("Player 1's turn now !");

            }
        }



    }

    Pair<Double,Double> movement(Double x,Double y,int result)
    {
        //RETURNS NEW POSITION
        Double newx=x;
        Double newy=y;

        if(newy%110==0) //direction
        {
            newx+= result*55;

            if(newx>550) //going right out
            {
                newx= 550*2 - newx +55;
                newy -=55;
            }

        } else
        {
            newx -= result * 55;

            if (newx <55) //going out left
            {
               newx=-1*(newx-55);
               newy-=55;
            }
            if(newy < - 495) //going outside up
            {
                return new Pair<>(x,y);
            }
        }
//        System.out.println(newx + " " + newy);
        if(snake_ladder_coordinates.containsKey(new Pair<>(newx,newy))) // snake or ladder present at current ?
        {
//            System.out.println("S or D Detected !");
            Pair<Double,Double> source = new Pair(newx,newy); //key for hashmap
            Double destx = snake_ladder_coordinates.get(source).getKey(); // dest x
            Double desty = snake_ladder_coordinates.get(source).getValue(); //dest y
            Pair<Double,Double> dest = new Pair<>(destx,desty); //dest
            return dest;
        }
        return new Pair<>(newx,newy);
    }
    void checkwin(int playerno,Double x,Double y) throws IOException {
        if(x==55 && y==-495)
        {
//            System.out.println("Player" + playerno + " is winner !");
            Alert winalert = new Alert(Alert.AlertType.INFORMATION);
            {
                winalert.setContentText("Player " + playerno + " won !");
                winalert.showAndWait();
                GamePage page = new GamePage();
                Main.root.getChildren().setAll(page.root);

            }
        }
    }
    void getSnakeLadderCoodinates()
    {
        snake_ladder_coordinates = new HashMap<>();
        //Board 2 DATA :
        //  1     2      3      4     5      6      7      8     9    10
        //  55    110   165    220   275    330    385    440   495   550
        //ADD LADDERS
        snake_ladder_coordinates.put(    new Pair<>(275.0,0.0),    new Pair<>(330.0,-110.0)); //5-26
        snake_ladder_coordinates.put(    new Pair<>(110.0,-55.0),    new Pair<>(55.0,-165.0)); //19-40
        snake_ladder_coordinates.put(    new Pair<>(440.0,-110.0),    new Pair<>(385.0,-275.0)); //28-54
        snake_ladder_coordinates.put(    new Pair<>(55.0,-275.0),    new Pair<>(110.0,-385.0)); //60-79
        snake_ladder_coordinates.put(    new Pair<>(330.0,-330.0),    new Pair<>(385.0,-440.0)); //66-87
        snake_ladder_coordinates.put(    new Pair<>(495.0,-385.0),    new Pair<>(550.0,-495.0)); //72-91
        snake_ladder_coordinates.put(    new Pair<>(275.0,-165.0),    new Pair<>(275.0,-385.0)); //36-76
        snake_ladder_coordinates.put(    new Pair<>(495.0,-55.0),    new Pair<>(550.0,-385.0)); //12-71

        //ADD SNAKES
        snake_ladder_coordinates.put(    new Pair<>(385.0,-110.0),    new Pair<>(330.0,0.0)); //27-6
        snake_ladder_coordinates.put(    new Pair<>(165.0,-220.0),    new Pair<>(495.0,0.0)); //43-9
        snake_ladder_coordinates.put(    new Pair<>(550.0,-220.0),    new Pair<>(550.0,-55.0)); //50-11
        snake_ladder_coordinates.put(    new Pair<>(330.0,-275.0),    new Pair<>(385.0,-165.0));//55-34
        snake_ladder_coordinates.put(    new Pair<>(220.0,-330.0),    new Pair<>(110.0,-110.0));//64-22
        snake_ladder_coordinates.put(    new Pair<>(440.0,-440.0),    new Pair<>(550.0,-275.0));//88-55
        snake_ladder_coordinates.put(    new Pair<>(385.0,-495.0),    new Pair<>(440.0,-220.0));//94-48
        snake_ladder_coordinates.put(    new Pair<>(165.0,-495.0),    new Pair<>(165.0,0.0));//98-3

    }

}