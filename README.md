# graph-route

Example project, which shows how to find the shortest route between two nodes of directed graph

# Graph BPMN Parser

# How to Run:
* mvn clean install
* java -jar target/graph-route.jar approveInvoice invoiceProcessed


# Domain Model:
* RoutePath class represents Directed Acyclic Graph
* RoutePath consists of edges
* Edge correspondes to RouteEdge class
* GraphRouteApp application finds minimum RoutePath between specified nodes
* GraphRouteApp application constructs the set of all possible directed acyclic graphs between specified nodes
* Minimum RoutePath is a Directed Acyclic Graph with minimum number of edges
* GraphRouteApp application displays minimum RoutePath as comma-delimited list of nodes
* GraphRouteApp application displays error message, if no RoutePath is found
