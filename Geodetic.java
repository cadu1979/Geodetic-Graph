/*
TODO change g.inicializaMatrizDistanciaMinimaUnica(); to some better name
TODO modify graph so that first line contains only the number of vertices
*/

import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

/* Tells if an undirected unweighted graph is geodetic. 
   A graph is geodetic if between each two vertices there exists an unique shortest path. */
public class Geodetic
{
    public static void main(String[] args)
    {
        Graph g = new Graph();
        List<String> graphDescription;
        Path graphDescriptionPath = Paths.get("grafo01.txt");
        int distanceMatrix[][] = new int[17][17];

        try
        {
            graphDescription = Files.readAllLines(graphDescriptionPath);
            g.buildGraph(graphDescription);
            FloydWarshall.findAllShortestPaths(g, distanceMatrix);
            if(g.isGeodetic())
                System.out.println("The graph is geodetic.");
            else
                 System.out.println("The graph is not geodetic.");
        }
        catch(Exception e) 
        {
			e.printStackTrace();
        }
    }
}

/*
ALG

reads graph from file
apply floyd-warshall alg
read result to tell if all shortest paths are unique
return result (text, then image)
*/
