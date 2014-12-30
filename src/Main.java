 import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    byte m_Arr[][];
    byte m_CloneArr[][];
    int m_N;

    Main() {

    }

    void initArr(int N) {
        //System.out.println("Inside initArr");
        m_N = N;
        m_Arr = new byte[N][N];


        cloneArr();
    }

    void cloneArr() {
        //System.out.println("Inside cloneArr");
        m_CloneArr = new byte[m_N][m_N];
//        for (int i = 0;i<m_N;++i) {
//            m_CloneArr[i] = new byte[m_N];
//        }


    }

    void updateSelfData(String fileName) {
        //System.out.println("Inside updateSelfData");
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    initArr(line.length());
                }



                //Only . and * are supported
                for (int j = 0;j<line.length();++j) {
                    m_Arr[i][j] = (byte)(line.charAt(j));
                }

                ++i;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    int neighBoursAliveOrDead(int x,int y) {

        //int arr[] = new int[2];
        int alive = 0;
        int dead = 0;
        for (int i = x-1;i<=(x+1);++i) {

            for (int j = y-1;j<=(y+1);++j) {

                if (i < 0 || j < 0 || i >= m_N || j >= m_N) {

                    continue; //Neighbours don't exist
                }

                if (i == x && j == y) {
                    continue; //Self
                }

                if (m_Arr[i][j] == '*') {
                    ++alive;
                }else {
                    ++dead;
                }
            }
        }

        //arr[0] = alive;
        //arr[1] = dead;

        return alive;
    }
    void transform() {

        byte tempArr[][] = m_CloneArr;
        for (int i = 0;i<m_N;++i) {
            for (int j = 0;j<m_N;++j) {
                //System.out.println(" i " + i + " " + "j" + j );
                int alive = neighBoursAliveOrDead(i,j);
                //System.out.println("Alive is " + alive );

                if (m_Arr[i][j] == '*') {
                    //Alive
                    if (alive < 2) {
                        m_CloneArr[i][j] = '.'; //under population
                    }else {
                        if (alive >=2 && alive <= 3) {
                            m_CloneArr[i][j] = '*'; //Lives on
                        }else {
                            m_CloneArr[i][j] = '.'; //Overcrowding
                        }
                    }
                }else {
                    if (alive == 3) {
                        m_CloneArr[i][j] = '*'; //Becomes alive
                    }else {
                        m_CloneArr[i][j] = '.'; //Be dead
                    }
                }
            }
        }

        byte [][] arr = m_Arr;
        m_Arr = m_CloneArr;
        m_CloneArr = arr;
    }

    void print() {

        for (int i =0;i<m_N;++i) {
            for (int j = 0;j<m_N;++j) {
                System.out.print((char)m_Arr[i][j]);
            }

            System.out.println("");
        }


    }

    public static void main(String args[]) {
        String fileName = args[0];
        Main gameOfLife = new Main();
        gameOfLife.updateSelfData(fileName);
        //gameOfLife.print();

        for (int i = 0;i<10;++i) {
            gameOfLife.transform();
        }

        gameOfLife.print();
    }
}
