package NumberOperations;

public class Main {

//    List<List<String> > permute(String x) {
//        if (x.length() == 1) {
//            return new LinkedList<String>(Arrays.asList(x));
//        }
//
//
//
//        String newString = x.substring(1);
//        //System.out.println("new string passed is " + newString);
//        char firstVal = x.charAt(0);
//        List<List<String>> permutations = permute(newString);
//        List<String> updatedPermutations = new ArrayList<>();
//        for (List<String> str : permutations) {
//            for (int i = 0;i<str.size()+1;++i) {
//                StringBuilder stringBuilder = new StringBuilder(str);
//                stringBuilder.insert(i,firstVal);
//                //System.out.println("updated str " + stringBuilder.toString());
//                updatedPermutations.add(stringBuilder.toString());
//            }
//        }
//
//        return updatedPermutations;
//    }
//
//    List<Integer> evalPartial(List<String> data,int index) {
//        if (index == data.size()-1) {
//            List<Integer> integers = new ArrayList<>();
//            integers.add(Integer.parseInt(data.get(index)));
//            return integers;
//        }
//
//        List<Integer> partialList = evalPartial(data,index+1);
//        Integer current = Integer.parseInt(data.get(index));
//
//        List<Integer> updatedList = new ArrayList<>();
//        for (Integer res : partialList) {
//            updatedList.add(current + res);
//            updatedList.add(current - res);
//            updatedList.add(current * res);
//        }
//
//        return updatedList;
//
//    }
//
//    void handleLine(String line) {
//        List<String> permutations = permute(line);
//        Integer current = Integer.parseInt(permutations.get(0));
//
//    }
//
//    void readFile(String fileName) {
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line;
//
//
//            while ((line = br.readLine()) != null) {
//                handleLine(line);
//
//            }
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String args[]) {
//
//        Main m = new Main();
//        m.readFile(args[0]);
//    }
}
