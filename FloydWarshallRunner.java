import java.util.Arrays;

// TODO description of the alg
// TODO modify alg to keep track of uniqueness of paths
// TODO specify that it is adapted to fit the geodetic problem

public class FloydWarshallRunner
{
    private int[][] shortestDistanceMatrix;
    private boolean[][] shortestPathIsUnique;

    public void setupAlgorithm(int adjMatrix[][])
    {
        shortestDistanceMatrix = Arrays.stream(adjMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    public int[][] runFloydWarshall(Graph g)
    {
        int adjMatrix[][] = g.getAdjacencyMatrix();
        this.setupAlgorithm(adjMatrix);
        int numVertices = g.getVertices().size();
        shortestPathIsUnique = new boolean[numVertices+1][numVertices+1];
        Util.setBoolMatrixToTrue(shortestPathIsUnique);
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
