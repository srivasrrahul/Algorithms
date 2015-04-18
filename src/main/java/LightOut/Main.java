package LightOut;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Configuration {
    public Configuration(ArrayList<ArrayList<Integer>> source) {
        this.arrayLists = new ArrayList<>();
        for (ArrayList<Integer> arr : source) {
            ArrayList<Integer> dest = (ArrayList<Integer>)arr.clone();
            arrayLists.add(dest);
        }

        //System.out.println(this.toString());
    }

    ArrayList<ArrayList<Integer>> getArrayLists() {
        return arrayLists;
    }

    int getVal(int i,int j) {
        return arrayLists.get(i).get(j);
    }

    void toggle(int i,int j) {
        arrayLists.get(i).set(j,(getVal(i,j) == 1?0:1));
        if (i > 0) {
            arrayLists.get(i-1).set(j,(getVal(i-1,j) == 1?0:1));
        }

        if (i<arrayLists.size()-1) {
            arrayLists.get(i + 1).set(j, (getVal(i + 1, j) == 1 ? 0 : 1));
        }

        if (j < arrayLists.get(0).size()-1) {
            arrayLists.get(i).set(j+1,(getVal(i,j+1) == 1?0:1));
        }

        if (j > 0) {
            arrayLists.get(i).set(j - 1, (getVal(i, j - 1) == 1 ? 0 : 1));
        }
    }

    boolean isAllOff() {
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer light : arrayList) {
                if (light == 1) {
                    return false;
                }
            }
        }

        //None is on
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration that = (Configuration) o;

        if (arrayLists != null ? !arrayLists.equals(that.arrayLists) : that.arrayLists != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return arrayLists != null ? arrayLists.hashCode() : 0;
    }

    @Override
    public String toString() {
        String prettyPrinting = "";
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer x : arrayList) {
                prettyPrinting += " ";
                prettyPrinting += x;
            }

            prettyPrinting += "\n";
        }

        return prettyPrinting;
    }

    private ArrayList<ArrayList<Integer>> arrayLists;
}
public class Main {
    int findMin(Configuration configuration,HashMap<Configuration,Integer> configurationsInPath) {
        //System.out.println("Inside loop");
        System.out.println(configuration.toString());
        if (configurationsInPath.containsKey(configuration)) {
            //This means cycle has been encountered

            System.out.println("Cycle is encountered");
            //System.out.println(configuration.toString());


            return Integer.MAX_VALUE;
        }

        if (configuration.isAllOff()) {
            System.out.println("Is all off");
            return 0;
        }

        int minPathLength = Integer.MAX_VALUE;
        configurationsInPath.put(configuration, Integer.MAX_VALUE);
        for (int i = 0;i<configuration.getArrayLists().size();++i) {
           for (int j = 0;j<configuration.getArrayLists().get(0).size();++j) {
               Configuration configuration1 = new Configuration(configuration.getArrayLists());
               //System.out.println("Toggling " + i + " " + j);
               configuration1.toggle(i,j);
//               HashMap<Configuration,Integer> configurationHashSetClone = (HashMap<Configuration,Integer>)configurationsInPath.clone();
//               configurationHashSetClone.put(configuration, Integer.MAX_VALUE); //Add current

               int pathLength = findMin(configuration1,configurationsInPath);
               if (pathLength < minPathLength) {

                   configurationsInPath.put(configuration1,pathLength);
                   minPathLength = pathLength+1;//for parent
               }else {
                   if (pathLength == Integer.MAX_VALUE) {
                       configurationsInPath.put(configuration1, Integer.MAX_VALUE);
                   }else {
                       configurationsInPath.put(configuration1, pathLength);
                   }
               }


           }
       }

        configurationsInPath.put(configuration, minPathLength);
        return minPathLength;
    }

    void handleLine(String line) {
        String arr[] = line.split(" ");
        int rowNumber = Integer.parseInt(arr[0]);
        int colNumber = Integer.parseInt(arr[1]);

        System.out.println(rowNumber + " " + colNumber);

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 0;i<rowNumber;++i) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 0;j<colNumber;++j) {
                arrayList.add(0);
            }

            arrayLists.add(arrayList);
        }



        String configArr[] = arr[2].split("\\|");

        for (int i = 0;i<configArr.length;++i) {
            //System.out.println(configArr[i]);
            for (int j = 0;j<configArr[i].length();++j) {
                char ch = configArr[i].charAt(j);
                if (ch == 'O') {
                    //System.out.println(arrayLists.size() + " " + i);
                    //System.out.println(arrayLists.get(i).size());
                    //System.out.println("Setting to 1");
                    arrayLists.get(i).set(j,1);
                }
            }
        }

        Configuration configuration = new Configuration(arrayLists);

        HashMap<Configuration,Integer> configurationHashSet = new HashMap<>();
        System.out.println(findMin(configuration,configurationHashSet));

    }
    void readFile(String fileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                handleLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]);
    }

}
