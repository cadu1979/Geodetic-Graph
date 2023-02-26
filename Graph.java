/*
TODO: do away with 'isPathMinimumLengthUnique' and do something else to do this functionality
*/

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

// GRAPH CLASS SHOULD ONLY HAVE GRAPH RELATED DATA AND FUNCTIONS, NOT FLOYD MARSHALL STUFF
public class Graph 
{
    private HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();

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
        int vertexID = getVertexID(lineData);
        int neighborsIDs[] = getNeighborsIDs(lineData);
        for(int nID: neighborsIDs)
            this.addEdge(vertexID, nID);
    }

    private int getVertexID(String lineData[])
    {
        return Integer.parseInt(lineData[0]);
    }

    private int[] getNeighborsIDs(String lineData[])
    {
        String neighborsIDs_str = Arrays.stream(lineData).filter(value -> value != lineData[0]).toArray();
        int neighborsIDs[] = toIntArray(neighborsData)
        return neighborsIDs;
    }

    private static int[] toIntArray(String strArray[])
    {
        int intArray[] = new Integer[strArray.length];
        for(int i = 0; i < strArray.length; i++)
        {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }

    /*
    // CAN THIS BE SUBSTITUTED BY JUST A CALL TO ADDEDGE?
    // if graph doesn't contain vertex, add it
    // adds edges from vertex to all neighbors in array
    //      if doesn't contain neighbor, add it
    // can be done with a series of addEdges
    private void addConections(int vertexID, int neighborsIDs[])
    {
        if(!vertices.containsKey(vertexID))
            this.addVertex(vertexID);
        for(Integer nID: neighborsIDs)
        {
            if(!vertices.containsKey(nID))
                this.addVertex(nID);
            this.addEdge(vertexID, nID);
        }
    }
    */

    // TODO rewrite -> 'distanciaMinimaUnica' will be changed later
    // ? DOES IT MAKE SENSE TO HAVE THIS METHOD IN THIS CLASS? SHOULDN'T IT BE IN A UTILITY CLASS?
    /* Caso o mapa 'distanciaMinimaUnica' indique que o caminho minimo entre um par de vertices (i, j) nao e unico, retorna false.
    Caso contrario, retorna true. */
    public boolean isGeodetic() 
    {
        for( Vertex v1: vertices.values() ) 
        {
            for( Vertex v2: vertices.values() ) 
            {
                Coord c = new Coord(v1.id, v2.id);
                if( distanciaMinimaUnica.get(c) == false )
                    return false;
            }
        }
        return true;
    }
}
