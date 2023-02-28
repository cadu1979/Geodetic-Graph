# Geodetic Graph

Calculates whether or not a graph is geodetic. A graph is geodetic if between each two vertices there exists an unique shortest path. 

The graph must be entered in a text file following the format given in the next section. 

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

![Graph Example](img\graph-example.png)

## PROJECT
IDEA
tells wether or not a graph is geodetic
deploy online

ENTRY
entry method will be either through .txt file or the user will be able to use a graphic interface to build the graph

ALGORITHM
to calculate if a graph is geodetic the floyd-warshall algorithm is used

RETURN
"Graph is geodetic" or "Graph isn't geodetic"
if isn't -> image showing at least one of the pair of vertices that have more than one path
if is -> image either listing all shortest paths or showing them in color on the graph

PROBLEMS
how to deploy java
