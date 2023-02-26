public class Util 
{
    private Util(){}

    public static int[] toIntArray(String strArray[])
    {
        int intArray[] = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++)
        {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }

    public static int[][] buildAdjacencyMatrix(Graph g)
    {
        int numVertices = g.getVertices().size();
        int[][] adjMatrix = new int[numVertices][numVertices];
        Vertex source, dest;
        for(int i = 0; i < adjMatrix.length; i++)
        {
            for(int j = 0; j < adjMatrix.length; j++)
            {
                source = g.getVertices().get(i);
                dest = g.getVertices().get(j);
                if(source == dest)
                    adjMatrix[i][j] = 0;
                else if(source.isNeighbor(dest))
                    adjMatrix[i][j] = 1;
                else
                    adjMatrix[i][j] = (int)(Integer.MAX_VALUE/2);
            }
        }
        return adjMatrix;
    }
}
