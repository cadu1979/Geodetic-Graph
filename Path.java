import java.util.Objects;

/* Represents a path in a graph by its source and dest vertices. 
The path (i, j) indicates a path beginnig at the vertex 'i' and ending at the vertex 'j'. 
Does not differentiate between different paths with same source and dest vertices. */
public class Path 
{
    private Integer sourceVertexID;
    private Integer destVertexID;

    public Path( Integer source, Integer dest )
    {
        this.sourceVertexID = source;
        this.destVertexID = dest;
    }

    public Integer getSource() 
    {
        return this.sourceVertexID;
    }

    public Integer getDest() 
    {
        return this.destVertexID;
    }

    // Allows a path to be identified in a HashMap by its source and dest.
    @Override
    public int hashCode() 
    {
        return Objects.hash( sourceVertexID, destVertexID );
    }

    // For two paths to be considered equal they need only have the same source and dest.
    @Override
    public boolean equals( Object o ) 
    {
        if( o == this ) return true;
        if( !(o instanceof Path) ) return false;

        Path c = (Path) o;
        if( this.sourceVertexID == c.getSource() && this.destVertexID == c.getDest() )
            return true;
        return false;
    }
}
