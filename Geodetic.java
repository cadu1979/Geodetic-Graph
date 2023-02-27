import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

/* Tells if an undirected unweighted graph is geodetic. 
   A graph is geodetic if between each two vertices there exists an unique shortest path. */
public class Geodetic
{
    private static Graph g;
    private static List<String> graphDescription;
    private static Path graphDescriptionPath;
    private static FloydWarshallRunner floydWarshall;

    public static void main(String[] args)
    {
        g = new Graph();
        graphDescriptionPath = Paths.get("graphDescription.txt");
        floydWarshall = new FloydWarshallRunner();
        try
        {
            graphDescription = Files.readAllLines(graphDescriptionPath);
            g.buildGraph(graphDescription);
            floydWarshall.runFloydWarshall(g);
            if(isGeodetic())
                System.out.println("The graph is geodetic.");
            else
                System.out.println("The graph is not geodetic.");
        }
        catch(Exception e) 
        {
			e.printStackTrace();
        }
    }

    public static boolean isGeodetic()
    {
        if(floydWarshall.allShortestPathsUnique())
            return true;
        else
            return false;
    }
}
