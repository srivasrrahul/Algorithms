package TicTacToe;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by rasrivastava on 6/29/15.
 */
public class TicTacGames {
    Random random = new Random();
    void play(TicTacState ticTacState) {
        TicTacUtilities ticTacUtilities = new TicTacUtilities();


        Scanner scanner = new Scanner(System.in);
        while (!ticTacUtilities.isFinishedState(ticTacState)) {
            System.out.println(ticTacState.toString());
            fillUserInput(ticTacState,scanner);
            System.out.println(ticTacState.toString());
            if (ticTacUtilities.isFinishedState(ticTacState) == true) {
                //User filled
                System.out.println("You won !!");
                break;
            }
            nextMove(ticTacState);
            //play(ticTacState);
            if (ticTacUtilities.isFinishedState(ticTacState) == true) {
                //User filled
                System.out.println("Computer won !!");
                break;
            }


        }


    }

    void fillUserInput(TicTacState ticTacState,Scanner scanner) {

        System.out.println("Enter row");
        int row = scanner.nextInt();

        System.out.println("Enter col");
        int col = scanner.nextInt();

        //No validation as of now
        ticTacState.setVal(row,col,TicTacConsts.FILL_CHAR);



    }

    void nextMove(TicTacState ticTacState) {
        int row = random.nextInt(ticTacState.getGameSize() - 1 + 1) + 0;
        int col = random.nextInt(ticTacState.getGameSize()-1 + 1) + 0;
        if (ticTacState.getVal(row,col) == TicTacConsts.FILL_CHAR) {
            //Discard current
            nextMove(ticTacState);
        }else {
            System.out.println("Filling " + row + " "  +  col);
            ticTacState.setVal(row, col, TicTacConsts.FILL_CHAR);
            //System.out.println(ticTacState.toString());
        }
    }


    public static void main(String[] args) {
        TicTacState ticTacState = new TicTacState(3);
        TicTacGames ticTacGames = new TicTacGames();
        ticTacGames.play(ticTacState);

    }
}
