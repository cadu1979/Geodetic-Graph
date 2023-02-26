/*
TODO: do away with 'isPathMinimumLengthUnique' and do something else to do this functionality
*/

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

// GRAPH CLASS SHOULD ONLY HAVE GRAPH RELATED DATA AND FUNCTIONS, NOT FLOYD MARSHALL STUFF
// Assumes the graph description detailed in README.md
public class Graph 
{
    private HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();

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

    public void print()
    {
        System.out.println("PRINTING");
        for (Vertex v : vertices.values()) 
        {
            System.out.println(v.getID());
            for (Vertex neighbor : v.getNeighbors().values()) 
            {
                System.out.println(neighbor.getID());
            }
            System.out.println();
        }
    }

    // A graph is geodetic if between each two vertices there exists an unique shortest path
    public boolean isGeodetic()
    {
        for(Vertex source: vertices.values())
        {
            for(Vertex dest: vertices.values())
            {
                if(!FloydWarshall.isShortestPathUnique(this, source, dest))
                    return false;
            }
        }
        return true;
    }

    // TODO rewrite -> 'distanciaMinimaUnica' will be changed later
    // ? DOES IT MAKE SENSE TO HAVE THIS METHOD IN THIS CLASS? SHOULDN'T IT BE IN A UTILITY CLASS?
    /* Caso o mapa 'distanciaMinimaUnica' indique que o caminho minimo entre um par de vertices (i, j) nao e unico, retorna false.
    Caso contrario, retorna true. */
    // public boolean isGeodetic() 
    // {
    //     for( Vertex v1: vertices.values() ) 
    //     {
    //         for( Vertex v2: vertices.values() ) 
    //         {
    //             Coord c = new Coord(v1.id, v2.id);
    //             if( distanciaMinimaUnica.get(c) == false )
    //                 return false;
    //         }
    //     }
    //     return true;
    // }
}
