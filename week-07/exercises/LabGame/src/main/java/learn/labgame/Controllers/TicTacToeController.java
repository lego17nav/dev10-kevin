package learn.labgame.Controllers;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeController {

    String[][] board = new String[3][3];
    Random rand = new Random();
    List<Integer> rowList = new ArrayList<>();


}
