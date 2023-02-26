/*
CHANGES
arq_ent -> sourceFile
"myfiles/grafo01.txt" -> "myfiles/graphDescription.txt"
*/

/*
TODO change g.inicializaMatrizDistanciaMinimaUnica(); to some better name
*/

//import java.io.FileReader;
//import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.List;
//import java.util.Scanner; 
import java.nio.file.Path;
import java.nio.file.Paths;

/* Tells if an undirected unweighted graph is geodetic. 
   A graph is geodetic if between each two vertices there exists an unique shortest path. */
public class Geodetic
{
    public static void main(String[] args)
    {
        Graph g = new Graph();
        //String sourceFile = "myfiles/graphDescription.txt";
        //File file = new File("myfiles/graphDescription.txt");
        //FileInputStream fileStream = new FileInputStream(file);

        //FileReader file = new FileReader("myfiles/graphDescription.txt");
        //BufferedReader buffReader = new BufferedReader(file);

        List<String> graphDescription;

        Path graphDescriptionPath = Paths.get("grafo01.txt");

        //List < String > lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);

        System.out.println(graphDescriptionPath);

        //String graphDescriptionFile;
        //Paths.get(graphDescriptionFile, "myfiles/graphDescription.txt");
        try
        {
            graphDescription = Files.readAllLines(graphDescriptionPath);
            g.buildGraph(graphDescription);
        }
        catch(Exception e) 
        {
			e.printStackTrace();
		}
        
        

        















		//g.open_text(file);

        //g.inicializaMatrizDistanciaMinimaUnica(); // DOESN'T FIT

        //g.Floyd_Warshall();

        // if (g.isGeodetic())
        //     System.out.println("The graph is geodetic.");
        // else
        //     System.out.println("The graph is not geodetic.");
    }
}

/*
ALG

reads graph from file
apply floyd-warshall alg
read result to tell if all shortest paths are unique
return result (text, then image)
*/
