package StringSubstitution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class FindReplaceTuple {
    private String find;
    private String replace;

    FindReplaceTuple(String find,String replace)  {
        this.find = find;
        this.replace = replace;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }
}

class Element {
    private byte val;
    private int nextIndex;
    private int replacedValueIndex;

    Element() {
        val = 0;
        nextIndex=0;
        replacedValueIndex = -1;
    }

    Element(byte val,int nextIndex) {
        this();
        this.val = val;
        this.nextIndex = nextIndex;
    }



    public byte getVal() {
        return val;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public int getReplacedValueIndex() {
        return replacedValueIndex;
    }

    public void setReplacedValueIndex(int replacedValueIndex) {
        this.replacedValueIndex = replacedValueIndex;
    }

    @Override
    public String toString() {
        return "Element{" +
                "val=" + val +
                ", nextIndex=" + nextIndex +
                ", replacedValueIndex=" + replacedValueIndex +
                '}';
    }
}
public class Main {

    ArrayList<Element> createElement(String data) {
        ArrayList<Element> arrayList = new ArrayList<>();
        int index = 1;
        for (Character character : data.toCharArray()) {
            byte val = (byte)(character - '0');
            arrayList.add(new Element(val,index));
            ++index;
        }

        return arrayList;
    }

    boolean isEqual(ArrayList<Element> elements,int startIndex,String orig) {
        int pendingSize = elements.size() - startIndex ;
        if (pendingSize < orig.length()) {
            return false;
        }

        for (int i = startIndex,j=0;j < orig.length() && i < elements.size();++i,++j) {
            Element element = elements.get(i);
            if (element.getVal() != (byte)(orig.charAt(j) - '0') || element.getReplacedValueIndex() != -1) {
                return false;
            }else {

            }
        }

        return true;
    }

    int updateVal(ArrayList<Element> elements,int index,int replacementIndex,int origSize) {
        Element element = elements.get(index);
        element.setReplacedValueIndex(replacementIndex);
        element.setNextIndex(index+origSize);
        //Element nextElement = elements.get(index + )
        return index+origSize;


    }
    ArrayList<Element> updateLst(ArrayList<Element> elements,String orig,int replacementIndex) {
        for (int i = 0;i<elements.size();) {
            //System.out.println("Inside for loop " + i);
            Element element = elements.get(i);
            if (element.getReplacedValueIndex() == -1) {
                //System.out.println("Can be replaced");
                //It can be replaced
                boolean compareData = isEqual(elements,i,orig);
                if (compareData == true) {
                    //System.out.println("Compare success orig => " + orig );
                    i = updateVal(elements, i, replacementIndex,orig.length());
                }else {
                    //System.out.println("Compare failure orig => " + orig );
                    ++i;
                    continue;
                }
            }else {
                //System.out.println("ALready replaced lets move to next " + element.getReplacedValueIndex());
                i = element.getNextIndex();
            }
        }

        return elements;
    }

    void handleLine(String line) {
        String [] data = line.split(";");

        String basicString = data[0];

        String [] findReplaceStrings = data[1].split(",");

        ArrayList<FindReplaceTuple> findReplaceTuples = new ArrayList<>();
        for (int i = 0;i<findReplaceStrings.length;) {
            findReplaceTuples.add(new FindReplaceTuple(findReplaceStrings[i],findReplaceStrings[i+1]));
            i += 2;
        }

       ArrayList<Element> elements = createElement(basicString);

        for (int i = 0;i<findReplaceTuples.size();++i) {
            updateLst(elements,findReplaceTuples.get(i).getFind(),i);
        }
//
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<elements.size();) {
            Element element = elements.get(i);
            if (element.getReplacedValueIndex() == -1) {
                //System.out.print(element.getVal());
                stringBuilder.append(element.getVal());
                ++i;
            }else {
                //Since only 0 now
                //System.out.print(findReplaceTuples.get(element.getReplacedValueIndex()).getReplace());
                stringBuilder.append(findReplaceTuples.get(element.getReplacedValueIndex()).getReplace());
                i = element.getNextIndex();

            }
        }

        System.out.println(stringBuilder.toString());

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



//    public static void main(String args[]) {
//        String data = "10011011001";
//        Main m = new Main();
//        ArrayList<Element> elements = m.createElement(data);
//        String f1 = "0110";
//        String r1 = "1001";
//        String f2 = "1001";
//        String r2 = "0";
//        String f3 = "10";
//        String r3 = "11";
//
//        for (Element element : elements) {
//            System.out.print(element.getVal());
//            //System.out.println(element);
//        }
//
//        System.out.println("==============Before first updation=============");
//        for (Element element : elements) {
//            //System.out.print(element.getVal());
//            System.out.println(element);
//        }
//
//        System.out.println("=============================");
//
//        elements = m.updateLst(elements,f1,0,r1.length());
//        System.out.println("==============After first updation==========");
//        for (Element element : elements) {
//            //System.out.print(element.getVal());
//            System.out.println(element);
//        }
//
//        System.out.println("=============print observed content after first updation============");
//        for (int i = 0;i<elements.size();) {
//            Element element = elements.get(i);
//            if (element.getReplacedValueIndex() == -1) {
//                System.out.print(element.getVal());
//                ++i;
//            }else {
//                //Since only 0 now
//                if (element.getReplacedValueIndex() == 0) {
//                    System.out.print(r1);
//                    i = element.getNextIndex();
//                }else {
//                    if (element.getReplacedValueIndex() == 1) {
//                        System.out.print(r2);
//                        i = element.getNextIndex();
//                    }else {
//                        System.out.println("Error");
//                    }
//                }
//            }
//        }
//        System.out.println();
//
//        elements = m.updateLst(elements,f2,1,r2.length());
//        System.out.println("==============After secon updation============");
//        for (Element element : elements) {
//            //System.out.print(element.getVal());
//            System.out.println(element);
//        }
//
//        System.out.println("=============print observed content after second updation============");
//        for (int i = 0;i<elements.size();) {
//            Element element = elements.get(i);
//            if (element.getReplacedValueIndex() == -1) {
//                System.out.print(element.getVal());
//                ++i;
//            }else {
//                //Since only 0 now
//                if (element.getReplacedValueIndex() == 0) {
//                    System.out.print(r1);
//                    i = element.getNextIndex();
//                }else {
//                    if (element.getReplacedValueIndex() == 1) {
//                        System.out.print(r2);
//                        i = element.getNextIndex();
//                    }else {
//                        System.out.println("Error");
//                    }
//                }
//            }
//        }
//
//        elements = m.updateLst(elements,f3,2,r3.length());
//        System.out.println("=============print observed content after third updation============");
//        for (int i = 0;i<elements.size();) {
//            Element element = elements.get(i);
//            if (element.getReplacedValueIndex() == -1) {
//                System.out.print(element.getVal());
//                ++i;
//            }else {
//                //Since only 0 now
//                if (element.getReplacedValueIndex() == 0) {
//                    System.out.print(r1);
//                    i = element.getNextIndex();
//                }else {
//                    if (element.getReplacedValueIndex() == 1) {
//                        System.out.print(r2);
//                        i = element.getNextIndex();
//                    }else if (element.getReplacedValueIndex() == 2) {
//                        System.out.print(r3);
//                        i = element.getNextIndex();
//                    }else {
//                        System.out.println("Error");
//                    }
//                }
//            }
//        }
//
//    }

}
