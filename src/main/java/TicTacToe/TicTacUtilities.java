package TicTacToe;


public class TicTacUtilities {

    boolean isFinishedState(TicTacState ticTacState) {
        //Finished state means one line is true
        for (int i = 0;i<ticTacState.getGameSize();++i) {
            if (true == isHorizontolLineSet(ticTacState,i)) {
                return true;
            }

            if (true == isVerticalLineSet(ticTacState,i)) {
                return true;
            }
        }

        if (true == isMainDiagonalSet(ticTacState)) {
            return true;
        }

        if (true == isReverseDiagonalSet(ticTacState)) {
            return true;
        }

        return false;
    }

    boolean isHorizontolLineSet(TicTacState ticTacState,int horizontolLineIndex) {
        for (int i = 0;i<ticTacState.getGameSize();++i) {
            if (ticTacState.getVal(horizontolLineIndex,i) == TicTacConsts.ZERO_CHAR) {
                return false;
            }
        }

        return true;
    }

    boolean isVerticalLineSet(TicTacState ticTacState,int verticalLineIndex) {
        for (int i = 0;i<ticTacState.getGameSize();++i) {
            if (ticTacState.getVal(i,verticalLineIndex) == TicTacConsts.ZERO_CHAR) {
                return false;
            }
        }

        return true;
    }

    boolean isMainDiagonalSet(TicTacState ticTacState) {
        for (int i = 0;i<ticTacState.getGameSize();++i) {
            if (ticTacState.getVal(i,i) == TicTacConsts.ZERO_CHAR) {
                return false;
            }
        }

        return true;
    }

    boolean isReverseDiagonalSet(TicTacState ticTacState) {
        for (int i = ticTacState.getGameSize()-1;i>=0;--i) {
            if (ticTacState.getVal(i,i) == TicTacConsts.ZERO_CHAR) {
                return false;
            }
        }

        return true;
    }


}
