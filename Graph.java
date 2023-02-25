/*
CHANGES
commented constructor
adicionaVertice -> addVertex
*/

/*
TODO
do away with 'isPathMinimumLengthUnique' and do something else to do this functionality
*/

import java.util.Collection;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;








// GRAPH CLASS SHOULD ONLY HAVE GRAPH RELATED DATA AND FUNCTIONS, NOT FLOYD MARSHALL STUFF












public class Graph 
{
    private HashMap< Integer, Vertex > vertices = new HashMap< Integer, Vertex >();
    private int verticesCount = 0;

    /*
    public Graph() 
    {
        //vertices = new HashMap< Integer, Vertex >();
        //verticesCount = 0;
    }
    */

    public void addVertex( Integer id ) 
    {
        if ( !vertices.containsKey( id ) ) 
        {
            Vertex v = new Vertex( id );
            vertices.put( v.id, v );
            verticesCount++;
        }
        else
            System.out.println("ID invalid or already in use");
    }

    /* Se um dos vertices nao estiver presente no grafo, retorna uma mensagem de erro.
    Se nao, adiciona o segundo vertice na vizinhanca do primeiro, e adiciona o primeiro vertice na vizinhanca do segundo. */
    /*
    public void adicionaAresta( Integer id1, Integer id2 ) 
    {
        Vertex v1 = vertices.get( id1 );
        Vertex v2 = vertices.get( id2 );
        if ( v1 == null || v2 == null ) 
        {
            System.out.printf("Vertice inexistente!");
            return;
        }
        v1.adicionaVizinho( v2 );
        v2.adicionaVizinho( v1 );
    }
    */
    public void addEdge(Integer sourceID, Integer destID) 
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

//============================================ WHERE TO PUT THIS? ===========================================================================================================

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

    /*
    public void print() 
    {
        System.out.printf("\n\nDados do Grafo, ");
        for( Vertex v : vertices.values())
            v.print();
    }
    */
    
// ====================================================== TO WHICH FILE PASS THIS FUNCTION? =========================================================================

    /* Abre o arquivo de texto que possui a entrada escrita na forma descrita no enunciado do trabalho. */
    public void open_text( String arq_ent ) 
    {
		String thisLine = null;
        vertices = new HashMap< Integer, Vertex >();
        distancia = new HashMap< Coord, Integer >();
        distanciaMinimaUnica = new HashMap< Coord, Boolean >();
		String pieces[ ];

		try 
        {
		    FileReader file_in = new FileReader( arq_ent );
		    BufferedReader br1 = new BufferedReader( file_in );
            
		    while ( (thisLine = br1.readLine( )) != null ) 
            {
			    // retira excessos de espaços em branco
			    thisLine = thisLine.replaceAll("\\s+", " ");
			    pieces = thisLine.split(" ");

                // caso o vertice nao exista no grafo, o adiciona
			    int v1Id = Integer.parseInt( pieces[0] );
                if( this.vertices.get( v1Id ) == null )
			        this.adicionaVertice( v1Id );

                // adiciona as arestas aos vertices adjacentes
			    for( int i = 2; i < pieces.length; i++ ) 
                {
   					int v2Id = Integer.parseInt( pieces[ i ] );
                    if( this.vertices.get( v2Id ) == null )
			            this.adicionaVertice( v2Id );
					this.adicionaAresta( v1Id, v2Id );
				}
		    }

            br1.close();
		} 
        catch(Exception e) 
        {
			e.printStackTrace();
		}
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
}
