package OrganizationalHiearchy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Rahul on 4/28/15.
 */

class Employee {
    private String id;
    private TreeMap<String,Employee> subordinates = new TreeMap<>();

    public Employee(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    void addEmployee(String id,Employee employee) {
        subordinates.put(id,employee);
    }

    Map<String,Employee> getSubOrdinates() {
        return subordinates;
    }

}

class Pair<T1,T2> {
    private T1 first;
    private T2 second;

    public Pair() {
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }
}
public class Main {

    Pair<Employee,Employee> getBossEmployee(String bossName,String employeeName,HashMap<String,Employee> fastScan,HashMap<String,Boolean> isChild) {
        //System.out.println("Adding boss employee " + bossName + " =>  " + employeeName);
        Pair<Employee,Employee> pair = new Pair();
        if (fastScan.containsKey(bossName)) {
            pair.setFirst(fastScan.get(bossName));
        }else {
            Employee employee = new Employee(bossName);
            fastScan.put(bossName,employee);
            pair.setFirst(employee);
        }



        if (fastScan.containsKey(employeeName)) {
            pair.setSecond(fastScan.get(employeeName));
        }else {
            Employee employee = new Employee(employeeName);
            fastScan.put(employeeName,employee);
            pair.setSecond(employee);
        }

        if (isChild.containsKey(bossName) == false){
            isChild.put(bossName,false);
        }

        isChild.put(employeeName,true);

        return pair;


    }
    void iterate(HashMap<String,Employee> fastScan,HashMap<String,Boolean> isChild) {
        Employee employee = getRoot(fastScan,isChild);
        //System.out.print("[");
        iterateRoot(employee);
        //System.out.print("]");
    }

    private void iterateRoot(Employee employee) {
        if (employee == null) {
            return;
        }

//        System.out.println();
//        System.out.println("emp " + employee.getId());
//        System.out.println();


        if (employee.getSubOrdinates().size() == 0) {
            System.out.print(employee.getId());
            return;
        }else {
            System.out.print(employee.getId() + " [");
            int index = 0;
            for (String reporteeName : employee.getSubOrdinates().keySet()) {
                if (index <= employee.getSubOrdinates().size()-1 && index > 0) {
                    System.out.print(", ");
                }
                iterateRoot(employee.getSubOrdinates().get(reporteeName));
                ++index;
            }
            System.out.print("]");
        }
    }

    private Employee getRoot(HashMap<String, Employee> fastScan,HashMap<String,Boolean> isChild) {
        for (String id : isChild.keySet()) {
            if (isChild.get(id) == false) {
                //System.out.println("Root is" + id);
                return fastScan.get(id);
            }
        }

        //System.out.println("Root is null");
        return null;
    }

    void formHiearchy(String relationShips[]) {
        HashMap<String,Boolean> isChild = new HashMap<>();
        HashMap<String,Employee> fastScan = new HashMap<>();
        for (String relationShip : relationShips) {
            String bossName = String.valueOf(relationShip.charAt(0));
            String employeeName = String.valueOf(relationShip.charAt(1));
            Pair<Employee,Employee> bossEmployee = getBossEmployee(bossName,employeeName,fastScan,isChild);
            bossEmployee.getFirst().addEmployee(employeeName,bossEmployee.getSecond());


        }

        iterate(fastScan,isChild);
    }
    void readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;


            while ((line = br.readLine()) != null) {
                String x[] = line.split("\\|");
                String y[] = new String[x.length];
                int i = 0;
                for (String val : x) {
                    y[i] = x[i].trim();
                    //System.out.println(y[i]);
                    ++i;

                }


                formHiearchy(y);
                System.out.println();

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Main m = new Main();
        m.readFile(args[0]);
    }
}
