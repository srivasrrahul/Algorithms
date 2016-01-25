package Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rasrivastava on 11/21/15.
 */
public class Util {
    static EdgeWeightedDigraph constructEdgeWeightedDirectedGraph(String fileName) throws IOException {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            int verticesCount = Integer.parseInt(line);
            //Ignore vertices as we don't need it
            bufferedReader.readLine();
            EdgeWeightedDigraph directedGraph = new EdgeWeightedDigraph(verticesCount);

            while ((line = bufferedReader.readLine()) != null) {
                String [] vertices = line.split(" ");
                int source = Integer.parseInt(vertices[0]);
                int dest = Integer.parseInt(vertices[1]);
                double d = Double.parseDouble(vertices[2]);

                directedGraph.addEdge(source,dest,d);
            }

            return directedGraph;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }


    }

    static Digraph constructDigraph(String fileName) throws IOException {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            int verticesCount = Integer.parseInt(line);
            //Ignore vertices as we don't need it
            bufferedReader.readLine();
            Digraph digraph = new Digraph(verticesCount);

            while ((line = bufferedReader.readLine()) != null) {
                String [] vertices = line.split(" ");
                int source = Integer.parseInt(vertices[0]);
                int dest = Integer.parseInt(vertices[1]);
                //double d = Double.parseDouble(vertices[2]);


            }

            return digraph;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }


    }
}
