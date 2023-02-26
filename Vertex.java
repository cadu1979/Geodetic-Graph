/*
CHANGES
initializes hashmap outside of constructor
adicionaVizinho -> addNeighbor
adjacente -> isNeighbor
added new line print to function 'print'
commented 'print' function
*/

import java.util.HashMap;

public class Vertex 
{
    private Integer id;
    private HashMap< Integer, Vertex > neighborhood = new HashMap< Integer, Vertex >(); 

    public Vertex ( Integer id ) 
    {
        this.id = id;
    }

    public Integer getID()
    {
        return this.id;
    }

    public HashMap<Integer, Vertex> getNeighbors()
    {
        return this.neighborhood;
    }

    public void addNeighbor( Vertex neighbor ) 
    {
        neighborhood.put( neighbor.getID(), neighbor );
    }

    public boolean isNeighbor( Vertex v ) 
    {
        if( neighborhood.get( v.getID() ) == null )
            return false;
        return true;
    }
};
