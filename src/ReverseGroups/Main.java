package ReverseGroups;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class Main {
    int getGroup(String line) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = line.length()-1;i>=0 && line.charAt(i) != ';';--i) {
            stringBuilder.insert(0, line.charAt(i));
        }

        //System.out.println(stringBuilder.toString());
        return Integer.parseInt(stringBuilder.toString());
    }

    void reverseGroupsFast(String string) {
        int groupLength = getGroup(string);
        StringBuilder intBuilder = new StringBuilder();
        int lastInt = 0;
        int index = 0;
        int loopedIndex = 0;
        List<Integer> stack = new LinkedList<>();
        for (char character : string.toCharArray()) {
            switch (character) {
                case ',':
                    lastInt = Integer.parseInt(intBuilder.toString());
                    //System.out.println("Last Int in case , is " + lastInt);
                    intBuilder.setLength(0); //Reset int builder
                    ++index;
                    ++loopedIndex;
                    //System.out.println("Looped index is " + loopedIndex + " Group Length " + groupLength);
                    stack.add(0, lastInt);
                    if (loopedIndex == groupLength) {
                        loopedIndex = 0;
                        while (stack.size() != 0) {
                            System.out.print(stack.remove(0) + ",");
                        }
                        //stack.push(lastInt);
                    }

                    //stack.push(lastInt);
                    lastInt = 0;

                    break;
                case ';':
                    lastInt = Integer.parseInt(intBuilder.toString());
                    //System.out.println("Last Int in case ; is " + lastInt);
                    intBuilder.setLength(0); //Reset int builder
                    ++index;
                    ++loopedIndex;
                    //System.out.println("Looped index is " + loopedIndex + " Group Length " + groupLength);
                    stack.add(0, lastInt);
                    if (loopedIndex == groupLength) {
                        loopedIndex = 0;
                        while (stack.size() != 0) {
                            int temp = stack.remove(0);
                            System.out.print(temp + (stack.size() == 0 ? "":","));
                        }
                        //stack.push(lastInt);
                    }else {
                        int s = stack.size();
                        ListIterator<Integer> listIterator = stack.listIterator(s);
                        while (listIterator.hasPrevious()) {
                            int temp = listIterator.previous();
                            System.out.print(temp + (listIterator.hasPrevious() ? ",":""));

                        }
                    }

                    //stack.push(lastInt);
                    lastInt = 0;




                    return;
                default:
                    intBuilder.append(character);
                    break;
            }
        }
    }

    void reverseGroups(String string) {
        String lst[] = string.split(";");
        int groupLength = Integer.parseInt(lst[1]);
        Stack<String> stringStack = new Stack<>();
        String numberArr[] = lst[0].split(",");
        int index = 0;
        //System.out.println(Arrays.toString(numberArr));

        for (String str : numberArr) {
            if ((index % groupLength) == 0) {
                //index = 1;
                while (!stringStack.empty()) {
                    if (index < numberArr.length) {
                        System.out.print(stringStack.pop() + ",");
                    }else {
                        //System.out.print("===" + index + " ");
                        System.out.print(stringStack.pop());
                    }
                }

                stringStack.push(str);
            }else {
                stringStack.push(str);
                //++index;
            }

            ++index;
        }

        if ((index % groupLength) == 0) {
            index = 0;
            while (!stringStack.empty()) {
                if (index < numberArr.length - 1) {
                    System.out.print(stringStack.pop() + ",");
                }else {
                    //System.out.print("===" + index + " ");
                    System.out.print(stringStack.pop());
                }

                ++index;
            }

            return;

        }

        //System.out.println("=====");
        int r = index % groupLength;
        for (int i = numberArr.length-stringStack.size();i< numberArr.length;++i) {
            if (i < numberArr.length-1) {
                System.out.print(numberArr[i] + ",");
            }else {
                System.out.print(numberArr[i]);
            }

        }
    }

    void handleLine(String line) {
        reverseGroupsFast(line);
        System.out.println();
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
//        Main m = new Main();
//        m.reverseGroups("1,2,3,4,5;3");
//    }
}
