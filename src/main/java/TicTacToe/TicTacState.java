package TicTacToe;


import java.util.ArrayList;

public class TicTacState {

    public TicTacState(int gameSize) {
        this.gameSize = gameSize;
        init();
    }

    private void init() {
        for (int i = 0;i<gameSize;++i) {
            ArrayList<Character> arrayList = new ArrayList<>();
            for (int j = 0;j<gameSize;++j) {
                arrayList.add('0');
            }

            game.add(arrayList);
        }
    }

    Character getVal(int row,int col) {
        //validate later
        return game.get(row).get(col);
    }

    void setVal(int row,int col,Character val) {
        game.get(row).set(col,val);
    }

    int getGameSize() {
        return gameSize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<gameSize;++i) {
            for (int j = 0;j<gameSize;++j) {
                stringBuilder.append(getVal(i,j));
                stringBuilder.append(" ");
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private int gameSize;
    private ArrayList<ArrayList<Character>> game = new ArrayList<>();


    //Test code
    public static void main(String[] args) {
        TicTacState ticTacState = new TicTacState(3);
        ticTacState.setVal(0,0,'X');
        ticTacState.setVal(2,1,'X');
        System.out.println(ticTacState.toString());
    }
}
