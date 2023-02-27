import java.util.Arrays;

/*
 * The Floyd-Warshall algorithm finds shortest paths between all pairs of vertices in a graph. 
 * The algorithm was modified to fit the problem of deciding whether or not a graph is geodetic. Now, aside from 
 * finding the shortest paths, it also keeps track of whether or not it found another path of similar length. 
 * For this, a 2D array (shortestPathIsUnique), containing information on whether or not the shortest path between a 
 * pair of vertices is the only path of such length between said vertices, was created. 
 */
public class FloydWarshallRunner
{
    private int[][] shortestDistanceMatrix;
    private boolean[][] shortestPathIsUnique;

    private void setupAlgorithm(int adjMatrix[][])
    {
        shortestDistanceMatrix = Arrays.stream(adjMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    private void setupGeodetic(Graph g)
    {
        int numVertices = g.getVertices().size();
        shortestPathIsUnique = new boolean[numVertices+1][numVertices+1];
        Util.setBoolMatrixToTrue(shortestPathIsUnique);
    }

    public int[][] runFloydWarshall(Graph g)
    {
        int adjMatrix[][] = g.getAdjacencyMatrix();
        setupAlgorithm(adjMatrix);
        setupGeodetic(g);
        for(int intermediateID = 1; intermediateID < shortestDistanceMatrix.length; intermediateID++)
        {
            for(int sourceID = 1; sourceID < shortestDistanceMatrix.length; sourceID++)
            {
                for(int destID = 1; destID < shortestDistanceMatrix.length; destID++)
                {
                    if((sourceID != destID) && (sourceID != intermediateID) && (intermediateID != destID))
                    {
                        if (shortestDistanceMatrix[sourceID][intermediateID] + 
                            shortestDistanceMatrix[intermediateID][destID] < 
                            shortestDistanceMatrix[sourceID][destID])
                        {
                            shortestDistanceMatrix[sourceID][destID] = 
                            shortestDistanceMatrix[sourceID][intermediateID] + 
                            shortestDistanceMatrix[intermediateID][destID];

                            shortestPathIsUnique[sourceID][destID] = true;
                            shortestPathIsUnique[destID][sourceID] = true;
                        }
                        else if(shortestDistanceMatrix[sourceID][destID] == 
                                shortestDistanceMatrix[sourceID][intermediateID] + 
                                shortestDistanceMatrix[intermediateID][destID])
                        {
                            shortestPathIsUnique[sourceID][destID] = false;
                            shortestPathIsUnique[destID][sourceID] = false;
                        }
                    }
                }
            }
        }
        return shortestDistanceMatrix;
    }

    public boolean allShortestPathsUnique()
    {
        for(int i = 0; i < shortestPathIsUnique.length; i++)
        {
            for(int j = 0; j < shortestPathIsUnique.length; j++)
            {
                if(shortestPathIsUnique[i][j] == false)
                    return false;
            }
        }
        return true;
    }
}
