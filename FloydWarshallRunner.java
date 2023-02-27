import java.util.HashMap;
import java.util.Arrays;

/* FloydWarshall.findAllShortestPaths(g, floydWarshallMatrix);
 * FloydWarshall.isShortestPathUnique(this, source, dest)
 */

// TODO description of the alg

public class FloydWarshallRunner
{
    private int[][] adjacencyMatrix;
    private int[][] shortestDistanceMatrix;

    // Integer.MAX_VALUE/2 is used as an alternative to infinity as there's a 
    // possibility of two of these values being summed, and any larger value
    // would overflow
    /*public void setupAdjacencyMatrix(Graph g)
    {
        adjacencyMatrix = this.buildAdjacencyMatrix(g);


        // for (int source = 1; source <= numberofvertices; source++) {
        //     for (
        //         int destination = 1;
        //         destination <= numberofvertices;
        //         destination++
        //     ) {
        //         DistanceMatrix[source][destination] =
        //             AdjacencyMatrix[source][destination];
        //     }
        // }
    }*/

    public void setupAlgorithm(Graph g)
    {
        this.buildAdjacencyMatrix(g);
        shortestDistanceMatrix = Arrays.stream(adjacencyMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    public void buildAdjacencyMatrix(Graph g)
    {
        int numVertices = g.getVertices().size();
        adjacencyMatrix = new int[numVertices][numVertices];
        Vertex source, dest;
        for(int i = 0; i < adjacencyMatrix.length; i++)
        {
            for(int j = 0; j < adjacencyMatrix.length; j++)
            {
                source = g.getVertices().get(i);
                dest = g.getVertices().get(j);
                if(source == dest)
                    adjacencyMatrix[i][j] = 0;
                else if(source.isNeighbor(dest))
                    adjacencyMatrix[i][j] = 1;
                else
                    adjacencyMatrix[i][j] = (int)(Integer.MAX_VALUE/2);
            }
        }
    }

    public int[][] runFloydWarshall(Graph g)
    {
        this.setupAlgorithm(g);
        for(int intermediateID = 1; intermediateID <= shortestDistanceMatrix.length; intermediateID++)
        {
            for(int sourceID = 1; sourceID <= shortestDistanceMatrix.length; sourceID++)
            {
                for(int destID = 1; destID <= shortestDistanceMatrix.length; destID++)
                {
                    if (shortestDistanceMatrix[sourceID][intermediateID] + 
                        shortestDistanceMatrix[intermediateID][destID] < 
                        shortestDistanceMatrix[sourceID][destID])
                    {
                        shortestDistanceMatrix[sourceID][destID] = 
                        shortestDistanceMatrix[sourceID][intermediateID] + 
                        shortestDistanceMatrix[intermediateID][destID];
                    }
                }
            }
        }
        return shortestDistanceMatrix;
    }

//!======================================================================================

    public void encontraCaminhosMinimos() 
    {
        for(Vertex v3: vertices.values()) 
        { // vertice intermediario no caminho de v1 ate v2
            ArrayList<Coord> visited = new ArrayList<Coord>(); //armazena os pares ja encontrados

            for(Vertex v1: vertices.values()) 
            { // vertice inicial do caminho atual
                for(Vertex v2: vertices.values()) 
                { // vertice final do caminho atual
                    Coord c1 = new Coord(v1.id, v2.id); //coordenadas do caminho atual
                    Coord c2 = new Coord(v1.id, v3.id); //primeira parte do novo caminho sendo comparado
                    Coord c3 = new Coord(v3.id, v2.id); //segunda parte do novo caminho sendo comparado
                    Coord oposta = new Coord(v2.id, v1.id); //coordenada do caminho atual no sentido oposto

                    /* Se um caminho menor e encontrado, atualiza o comprimento do caminho minimo para o par (v1, v2) nos 
                    dois sentidos, atualiza o caminho como 'unico', e impede que o caminho no sentido contrario seja calculado. */
                    if(v1.id != v2.id && v1.id != v3.id && v2.id != v3.id && 
                        distancia.get(c1) > distancia.get(c2) + distancia.get(c3)) 
                    {
                        distancia.put(c1, distancia.get(c2) + distancia.get(c3));
                        distancia.put(oposta, distancia.get(c1));
                        distanciaMinimaUnica.put(c1, true);
                        distanciaMinimaUnica.put(oposta, true);
                        if(!visited.contains(oposta)) 
                            visited.add(oposta);
                    }
                    /* Se um caminho com comprimento igual existir, atualiza o caminho para nao ser considerado unico. */
                    else if(v1.id != v2.id && v1.id != v3.id && v2.id != v3.id && 
                        distancia.get(c1) == distancia.get(c2) + distancia.get(c3) 
                        && !visited.contains(c1)) 
                    {
                        distanciaMinimaUnica.put(c1, false);
                        distanciaMinimaUnica.put(oposta, false);
                    }
                }
            }
        }
    }

    private HashMap< Path, Integer > pathsLengths; // WRITE ITS USES? PUT IT CLOSER TO WHERE ITS USED?

    /* Mapa cuja coordenada (i,j) diz se o caminho minimo do vertice 'i' ao vertice 'j' é unico. Se sim, o valor correspondente e 'true'. */
    private HashMap< Path, Boolean > isPathMinimumLengthUnique; // BAD NAME. SHOULDN'T THIS BE THE RESPONSIBILITY OF A FUNCTION OR SMTH ELSE?

    private FloydWarshall(){}

    /* Cada elemento do mapa 'distancia 'é inicializado para o maior valor possivel.
    O valor nao pode ser Integer.MAX_VALUE pois as contas realisada poderiam resultar em overflow, 
    mas como o maior valor que um elemento nessa matriz pode assumir e o dobro do valor com o qual e inicializado, 
    pode ser dado o valor maximo de (Integer.MAX_VALUE/2). */
    public void inicializaMatrizDistancia() 
    {
        for( Vertex v1: vertices.values() ) 
        {
            for( Vertex v2: vertices.values() ) 
            {
                Coord c = new Coord(v1.id, v2.id);
                if( v1.id == v2.id ) 
                    distancia.put( c, 0 ); // caminho entre um vertice e si mesmo e igaul a 0
                else if( v1.vizinhanca.containsKey( v2.id ) ) 
                    distancia.put( c, 1 ); // caminho entre um vertice e um vertice adjacente e igaul a 1
                else
                    distancia.put( c, (int)(Integer.MAX_VALUE/2) );
            }
        }
    }

    public void setIsPathMinimumLengthUnique(HashMap<Path, Boolean> isPathMinimumLengthUnique) {
        this.isPathMinimumLengthUnique = isPathMinimumLengthUnique;
    }

    /* As coordenadas correspondentes a lacos sao inicializados com 'true', assim como as correspondentes a arestas do grafo:
    os menores caminhos possiveis entre um vertice e si mesmo seria um laco, entre dois vertices distintos seria uma aresta. */
    public void inicializaMatrizDistanciaMinimaUnica() 
    {
        for( Vertex v1: vertices.values() ) 
        {
            for( Vertex v2: vertices.values() ) 
            {
                Coord c = new Coord(v1.id, v2.id);
                if( v1.id == v2.id || v1.adjacente( v2 ) )
                    distanciaMinimaUnica.put( c, true );
                else
                    distanciaMinimaUnica.put( c, false );
            }
        }
    }

    /* Encontra o caminho minimo entre todos os pares de vertices no grafo.
    Compara todos os possiveis caminhos entre todos os pares de vertices. O algoritmo estima o caminho minimo entre dois vertices, 
    e com cada iteracao, aprimora sua estimativa ate encontrar o caminho minimo.

    O codigo escolhe um vertice como intermediario, entao calcula caminhos entre todos os pares de vertices que passem por esse vertice.
    Caso encontre um caminho menor para um par de vertices, atualiza o comprimento do caminho minimo entre o par em questao.
    Repete o processo descrito tomando cada vertice como intermediario, ate que todos os vertices tenham sido testados.
    No final de sua execucao, temos o mapa 'distancia' armazenando o comprimento do caminho minimo entre todos os pares de vertices, 
    e o mapa 'distanciaMinimaUnica' armazenando se o caminho minimo entre cada par de vertices e unico. 

    O(V^3), sendo V o numero de vertices no grafo */
    public void encontraCaminhosMinimos() 
    {
        for(Vertex v3: vertices.values()) 
        { // vertice intermediario no caminho de v1 ate v2
            ArrayList<Coord> visited = new ArrayList<Coord>(); //armazena os pares ja encontrados

            for(Vertex v1: vertices.values()) 
            { // vertice inicial do caminho atual
                for(Vertex v2: vertices.values()) 
                { // vertice final do caminho atual
                    Coord c1 = new Coord(v1.id, v2.id); //coordenadas do caminho atual
                    Coord c2 = new Coord(v1.id, v3.id); //primeira parte do novo caminho sendo comparado
                    Coord c3 = new Coord(v3.id, v2.id); //segunda parte do novo caminho sendo comparado
                    Coord oposta = new Coord(v2.id, v1.id); //coordenada do caminho atual no sentido oposto

                    /* Se um caminho menor e encontrado, atualiza o comprimento do caminho minimo para o par (v1, v2) nos 
                    dois sentidos, atualiza o caminho como 'unico', e impede que o caminho no sentido contrario seja calculado. */
                    if(v1.id != v2.id && v1.id != v3.id && v2.id != v3.id && 
                        distancia.get(c1) > distancia.get(c2) + distancia.get(c3)) 
                    {
                        distancia.put(c1, distancia.get(c2) + distancia.get(c3));
                        distancia.put(oposta, distancia.get(c1));
                        distanciaMinimaUnica.put(c1, true);
                        distanciaMinimaUnica.put(oposta, true);
                        if(!visited.contains(oposta)) 
                            visited.add(oposta);
                    }
                    /* Se um caminho com comprimento igual existir, atualiza o caminho para nao ser considerado unico. */
                    else if(v1.id != v2.id && v1.id != v3.id && v2.id != v3.id && 
                        distancia.get(c1) == distancia.get(c2) + distancia.get(c3) 
                        && !visited.contains(c1)) 
                    {
                        distanciaMinimaUnica.put(c1, false);
                        distanciaMinimaUnica.put(oposta, false);
                    }
                }
            }
        }
    }

    /* Aplica o algoritmo Floyd-Warshall para encontrar os caminho minimos entre todos os pares de vertices. */
    public void Floyd_Warshall () 
    {
        inicializaMatrizDistancia();
        encontraCaminhosMinimos();
    }

}