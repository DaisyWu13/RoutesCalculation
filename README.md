This project mainly supplies some methods to compute routes between two given vertexes

1.Vertex
This class is to describe the point in graph, it has 2 members, index and value, and the value can be any type of an object.

2.Edge
Maily for describing a route with a start point, an ending point, and the distance.

3.Graph
The graph stores the key value of vertex, the index in vertex maps to the index of vertex in vertex list, 
and the distance between vertex, the row and colum index maps to index of vertex in vertex list.

It offers two kinds of algorithms, one is to compute the shortest distance between two points, another is to compute the numbers of routes between points.

- dijkstra
![djkstra diagram](image/dijkstra.png)

- routesNum
![routesNum diagram](image/routes-num.png)

- routesNumLimit
![routesNumLimit diagram](image/routes-num-limit.png)

- computeDistance
![computeDistance diagram](image/compute-distance.png)

4.Graph Action(command of graph action)

- DijkstraAction( a pakage of dijkstra of the graph)

- RoutesNumAction( a pakage of routesNum of the graph)

- RoutesNumLimitedAction( a pakage of routesNumLimit of the graph)

- ComputeDistanceAction( a pakage of computeDistance of the graph)

5.GraphInvoker
A intermediary between graph and mandator.

6.RouteOfString
This class mainly describes a route, which has a graph in. The mainly works here is to init a graph describing train routes.
- CreateGraph
- initEdges

7.Demo
Here is the demo, containing 10 outputs for the shortest distance and how many the routes is between points.

8.How to use
The graph pakage is composed of the route algorithm and the graph model, the value in the vertex could be anything.

e.i.

    List<String> list = readEdgeFromTxt(txtPath);
    Route route = new RouteOfString(list);
    ComputeDistanceAction action = new ComputeDistanceAction(this.route.getGraph(), indexs);
    GraphInvoker invoker = new GraphInvoker(action);
    invoker.runAction();
    distance = action.getResult();


