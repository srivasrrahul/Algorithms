package MinDistance;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

class TestClass {


    ArrayList<ArrayList<Long>> allPairsShortestPath(ArrayList<ArrayList<Integer>> graph) {
        ArrayList<ArrayList<Long>> distance = new ArrayList<>();
        for (int i = 0;i<graph.size();++i) {
            distance.add(new ArrayList<Long>());
            for (int j = 0;j<graph.size();++j) {
                distance.get(i).add((long)Integer.MAX_VALUE);

            }




        }

        for (int i = 0;i<graph.size();++i) {

            distance.get(i).set(i,0L);




        }

        for (int i = 0;i<graph.size();++i) {


            for (int j = 0;j<graph.size();++j) {
                if (graph.get(i).get(j) != Integer.MAX_VALUE) {
                    //There is an edge
                    //System.out.println("Edge between " + i + " " + j);
                    distance.get(i).set(j,(long)graph.get(i).get(j));
                    //distance.get(j).set(i,graph.get(i).get(j));
                }


            }


        }


        for (int i = 0;i<graph.size();++i) {
            //System.out.println("Inside i " + i);
            for (int j = 0;j<graph.size();++j) {
                //System.out.println("Inside j " + j);
                for (int k = 0;k<graph.size();++k) {
                    //System.out.println("Inside k " + k);
                    if (distance.get(j).get(k) > distance.get(j).get(i) + distance.get(i).get(k)) {
                        //System.out.println("Relax j k " + j + " " + k);
                        distance.get(j).set(k,distance.get(j).get(i) + distance.get(i).get(k));
                    }
                }
            }
        }

        return distance;

    }

    void print(ArrayList<ArrayList<Long>> arrayLists) {
        for (int i = 0;i<arrayLists.size();++i) {
            for (int j = 0;j<arrayLists.size();++j) {
                System.out.print(arrayLists.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String data [] = line.split(" ");
        int stations = Integer.parseInt(data[0]);
        int directConnections = Integer.parseInt(data[1]);

        line = br.readLine();
        HashMap<String,Integer> stationNames = new HashMap<>();
        data = line.split(" ");
        for (int i = 0;i<data.length;++i) {
            stationNames.put(data[i],i);
        }

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 0;i<stations;++i) {
            arrayLists.add(new ArrayList<Integer>());
            for (int j = 0 ;j<stations;++j) {
                arrayLists.get(i).add(Integer.MAX_VALUE);
                if (i == j) {
                    arrayLists.get(i).set(j, 0);
                }
            }
        }

        for (int i = 0;i<directConnections;++i) {
            line = br.readLine();
            data = line.split(" ");
            int s1 = stationNames.get(data[0]);
            int s2 = stationNames.get(data[1]);
            int length = Integer.parseInt(data[2]);
            arrayLists.get(s1).set(s2,length);
            arrayLists.get(s2).set(s1,length);
        }

        ArrayList<ArrayList<Long>> distance = testClass.allPairsShortestPath(arrayLists);
        //testClass.print(distance);

        line = br.readLine();
        int queries = Integer.parseInt(line);
        //System.out.println("Number of queries " + queries);
        for (int i = 0;i<queries;++i) {
            line = br.readLine();
            data = line.split(" ");

            int s1 = stationNames.get(data[0]);
            int s2 = stationNames.get(data[1]);

            //System.out.println("Query from " + s1 + " " + s2);

            System.out.println(distance.get(s2).get(s1));
        }
    }

}
