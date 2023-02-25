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
        //neighborhood = new HashMap< Integer, Vertex >();
    }

    public Integer getID()
    {
        return this.id;
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

    /*
    // prints IDs of this vertex and neighbors
    public void print() 
    {
        System.out.print("\nVertex's ID: " + id + ", Neighborhood: " );
        for( Vertex v : neighborhood.values() )
            System.out.print(" " + v.getID() );
        System.out.print("\n" );
    }
    */
};
