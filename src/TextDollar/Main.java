package TextDollar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


//Not optimized
public class Main {
    static HashMap<Character,String> text = new HashMap<>();
    static HashMap<Character,String> textInTens = new HashMap<>();
    static HashMap<Integer,String> units = new HashMap<>();
    static {
        //text.put('0',"Zero");
        text.put('1',"One");
        text.put('2',"Two");
        text.put('3',"Three");
        text.put('4',"Four");
        text.put('5',"Five");
        text.put('6',"Six");
        text.put('7',"Seven");
        text.put('8',"Eight");
        text.put('9',"Nine");

        //textInTens.put('0',"Zero");
        textInTens.put('1',"Ten");
        textInTens.put('2',"Twenty");
        textInTens.put('3',"Thirty");
        textInTens.put('4',"Forty");
        textInTens.put('5',"Fifty");
        textInTens.put('6',"Sixty");
        textInTens.put('7',"Seventy");
        textInTens.put('8',"Eighty");
        textInTens.put('9',"Ninety");

        units.put(0,"");
        units.put(1,"");
        units.put(2,"Thousand");
        units.put(3,"Million");
        units.put(4,"Billion");


    }

    List<String> splitStringFromLast(String dollars,int n) {
        List<String> stringList = new LinkedList<>();
        int j = dollars.length()-1;

        while (j >= 0) {
            if (j < n ) {
                //Add last and break
                String pendingString = dollars.substring(0,j+1);
                stringList.add(0,pendingString);
                break;
            }

            String pendingString = dollars.substring(j-n+1,j+1);
            stringList.add(0,pendingString);
            j = j - n;
        }

        return stringList;
    }

    //Always assume string length is three
    String getOnes(String character) {
        return text.get(character.charAt(2));
    }
    String getTens(String character) {

        return textInTens.get(character.charAt(1));
    }
    String getHundereds(String x) {
        String first = text.get(x.charAt(0));
        return first + "Hundred";
    }


    String getStringInUnits(String dollarsInHunderd) {
        StringBuilder retValue = new StringBuilder();

        if (dollarsInHunderd.length() == 1) {
            //return text.
            //return getHundereds("00" + dollarsInHunderd);
            dollarsInHunderd = "00" + dollarsInHunderd;
        }else if (dollarsInHunderd.length() == 2) {
            //return getHundereds("0" + dollarsInHunderd);
            dollarsInHunderd = "0" + dollarsInHunderd;
        }

        //System.out.println(" UpdatedStr " + "=>" + dollarsInHunderd);

        for (int i = 0; i < dollarsInHunderd.length(); ++i) {
            if (i == 0 && dollarsInHunderd.charAt(i) != '0') {
                retValue.append(getHundereds(dollarsInHunderd));
            }

            if (i == 1 && dollarsInHunderd.charAt(i) != '0') {
                retValue.append(getTens(dollarsInHunderd));
            }

            if (i == 2 && dollarsInHunderd.charAt(2) != '0') {
                retValue.append(getOnes(dollarsInHunderd));
            }

        }

        return retValue.toString();

    }

    String units(int index) {
        return units.get(index);
    }
    String textDollars(String dollars){

        List<String> stringList = splitStringFromLast(dollars,3);
        int unitIndex = stringList.size();
        //String text = new String();
        StringBuilder text = new StringBuilder();

        for (String str : stringList) {
             text.append(getStringInUnits(str));
             text.append(units(unitIndex));
            //System.out.println(units(unitIndex) + " " + getStringInUnits(str));
            --unitIndex;
        }

        text.append("Dollars");
        return text.toString();
    }

    StringBuilder getStringInUnitsFast(String dollarsInHunderd,int unitIndex,StringBuilder retValue) {
        if (dollarsInHunderd.length() == 1) {
            //return text.
            //return getHundereds("00" + dollarsInHunderd);
            dollarsInHunderd = "00" + dollarsInHunderd;
        }else if (dollarsInHunderd.length() == 2) {
            //return getHundereds("0" + dollarsInHunderd);
            dollarsInHunderd = "0" + dollarsInHunderd;
        }

        //System.out.println(" UpdatedStr " + "=>" + dollarsInHunderd);

        for (int i = 0; i < dollarsInHunderd.length(); ++i) {
            if (i == 0 && dollarsInHunderd.charAt(i) != '0') {
                retValue.append(getHundereds(dollarsInHunderd));
            }

            if (i == 1 && dollarsInHunderd.charAt(i) != '0') {
                retValue.append(getTens(dollarsInHunderd));
            }

            if (i == 2 && dollarsInHunderd.charAt(2) != '0') {
                retValue.append(getOnes(dollarsInHunderd));
            }

        }

        retValue.append(units(unitIndex));
        return retValue;

    }

    String textDollarsFast(String dollars){

        List<String> stringList = splitStringFromLast(dollars,3);
        int unitIndex = stringList.size();
        //String text = new String();
        StringBuilder text = new StringBuilder();

        for (String str : stringList) {
            //text.append(getStringInUnits(str));
//            /text.append(units(unitIndex));
            getStringInUnitsFast(str,unitIndex,text);
            //System.out.println(units(unitIndex) + " " + getStringInUnits(str));
            --unitIndex;
        }

        text.append("Dollars");
        return text.toString();
    }


    void handleLine(String line) {

//        long t3 = System.nanoTime();
//        System.out.println(textDollars(line));
//        long t4 = System.nanoTime();
//        System.out.println("Time in slow " + (t4-t3));
//
//        long t1 = System.nanoTime();
        System.out.println(textDollarsFast(line));
//        long t2 = System.nanoTime();
//        System.out.println("Time in fast " + (t2-t1));





    }
    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
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
