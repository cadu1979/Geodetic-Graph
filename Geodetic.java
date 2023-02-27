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
    private static int shortestDistancesMatrix[][];
    private static FloydWarshallRunner floysWarshall;

    public static void main(String[] args)
    {
        g = new Graph();
        graphDescriptionPath = Paths.get("grafo01.txt");
        floysWarshall = new FloydWarshallRunner();
        try
        {
            graphDescription = Files.readAllLines(graphDescriptionPath);
            g.buildGraph(graphDescription);
            int numVertices = g.getVertices().size();
            shortestDistancesMatrix = new int[numVertices][numVertices];
            shortestDistancesMatrix = floysWarshall.runFloydWarshall(g);
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

    /*
     * A matrix storing the value of the shortest path so far
     * 
     * A counter that up to 2
     * func 'isGeodetic' iterates through shortestDistancesMatrix and looks for paths with length equaling known shortest path length
     * for each path found, counter goes up 1
     * if counter == 2, return false
     */
    /*
     * For a given (source, dest), check for multiple shortest paths
     */
    /*public static boolean isGeodetic()
    {
        for(int sourceID = 1; sourceID < shortestDistancesMatrix.length; sourceID++)
        {
            for(int destID = 1; destID < shortestDistancesMatrix.length; destID++)
            {
                if(!shortestPathIsUnique(sourceID, destID))
                    return false;
            }
        }
        return true;
    }*/

    public static boolean isGeodetic()
    {
        if(floysWarshall.allShortestPathsUnique())
            return true;
        else
            return false;
    }

    // ! WRONG: long lines are geodetic, but this solution says they're not
    //? NEED THE PATHS TO TELL IF THEY'RE UNIQUE?
    public static boolean shortestPathIsUnique(int sourceID, int destID)
    {
        int numShortestPaths = 0;
        for(int intermediateID = 1; intermediateID < shortestDistancesMatrix.length; intermediateID++)
        {
            if((sourceID != destID) && (sourceID != intermediateID) && (destID != intermediateID))
            {
                if (shortestDistancesMatrix[sourceID][destID] == 
                    shortestDistancesMatrix[sourceID][intermediateID] + 
                    shortestDistancesMatrix[intermediateID][destID])
                {
                    numShortestPaths++;
                    if(numShortestPaths > 1)
                        return false;
                }
            }
        }
        return true;
    }
}
