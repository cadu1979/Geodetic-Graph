import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public class Graph 
{
    private HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
    private int[][] adjacencyMatrix;

    public HashMap<Integer, Vertex> getVertices()
    {
        return this.vertices;
    }

    public void addVertex(int id) 
    {
        if (!vertices.containsKey(id)) 
        {
            Vertex v = new Vertex(id);
            vertices.put(id, v);
        }
    }

    public void addEdge(int sourceID, int destID) 
    {
        if(!vertices.containsKey(sourceID))
            this.addVertex(sourceID);
        if(!vertices.containsKey(destID))
            this.addVertex(destID);
        Vertex source = vertices.get(sourceID);
        Vertex dest = vertices.get(destID);
        source.addNeighbor(dest);
        dest.addNeighbor(source);
    }

    public void buildGraph(List<String> graphDescription)
    {
        String lineData[];
        for(String line: graphDescription)
        {
            lineData = line.split(" "); 
            this.addLineDataToGraph(lineData);
        }
    }

    private void addLineDataToGraph(String lineData[])
    {
        int vertexID = extractVertexID(lineData);
        int neighborsIDs[] = extractNeighborsIDs(lineData);
        for(int nID: neighborsIDs)
            this.addEdge(vertexID, nID);
    }

    private int extractVertexID(String lineData[])
    {
        return Integer.parseInt(lineData[0]);
    }

    private int[] extractNeighborsIDs(String lineData[])
    {
        String neighborsIDs_str[] = ArrayUtils.removeElement(lineData, lineData[0]);
        int neighborsIDs[] = Util.toIntArray(neighborsIDs_str);
        return neighborsIDs;
    }

    public void buildAdjacencyMatrix()
    {
        int numVertices = this.getVertices().size();
        adjacencyMatrix = new int[numVertices+1][numVertices+1];
        Vertex source, dest;
        for(int sourceID = 0; sourceID < adjacencyMatrix.length; sourceID++)
        {
            for(int destID = 0; destID < adjacencyMatrix.length; destID++)
            {
                if(getVertices().containsKey(sourceID) && getVertices().containsKey(destID))
                {
                    source = this.getVertices().get(sourceID);
                    dest = this.getVertices().get(destID);
                    if(source == dest)
                        adjacencyMatrix[sourceID][destID] = 0;
                    else if(source.isNeighbor(dest))
                        adjacencyMatrix[sourceID][destID] = 1;
                    else
                        adjacencyMatrix[sourceID][destID] = (int)(Integer.MAX_VALUE/2);
                }
            }
        }
    }

    public int[][] getAdjacencyMatrix()
    {
        if(adjacencyMatrix == null)
            buildAdjacencyMatrix();
        return this.adjacencyMatrix;
    }

    public void printGraphDescription()
    {
        for (Vertex v : vertices.values()) 
        {
            System.out.println("Vertex: " + v.getID());
            System.out.print("Neighbors: ");
            for (Vertex neighbor : v.getNeighbors().values()) 
            {
                System.out.print(neighbor.getID() + " ");
            }
            System.out.println("\n");
        }
    }
}
