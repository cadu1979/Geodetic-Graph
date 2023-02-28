# Geodetic Graph

Calculates whether or not a graph is geodetic. A graph is geodetic if between each two vertices there exists an unique shortest path. This implementation utilizes the Floyd-Warshall algorithm to find all the shortest paths and, while doing so, checks if the shortest paths are unique. 

The graph must be entered in a text file (graphDescription.txt) following the format given in the next section. 

Prints a string telling whether or not the graph is geodetic. 

## Correct form of the graph's description

The description must contain only the IDs of the vertices, which are integers.
Each line in the description must start with the ID of a vertex and be followed by the IDs of that vertex's neighbors.
Each ID must be separated by a single space.

An example of the correct description for the graph pictured below would be:

1 2 4  
2 1 3 4  
3 2 4  
4 1 2 3  

![Graph Example](https://github.com/cadu1979/Geodetic-Graph/blob/main/img/graph-example.png?raw=true)
