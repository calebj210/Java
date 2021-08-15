package cs2321;

import net.datastructures.*;

/**
 * Collection of graph path finding algorithms
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 21/04/2021
 * Course: CS2321 Spring 2021
 */
public class Travel {
	AdjacencyGraph<String, Integer> G;			// City graph
	Map<String, Vertex<String>> vertices;		// Map of vertices
	
	/**
	 * Construct a graph from the routes input data
	 * 
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *                
	 *                Example:
	 *                  routes = {  {"A","B","8"},
	 *							    {"A","D","1"},
	 *							    {"B","C","11"},
	 * 							    {"C","D","1"}
	 *                           };
	 *                           
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 */
	public Travel(String [][] routes) {
		G = new AdjacencyGraph<String, Integer>(false);
		vertices = new HashMap<String, Vertex<String>>();
		
		for (int i = 0; i < routes.length; i++) {
			String city1 = routes[i][0];					// Name of first city
			String city2 = routes[i][1];					// Name of second city
			int    cost  = Integer.valueOf(routes[i][2]);	// Cost from first to second city
			
			// Check if city 1 exists yet
			if (vertices.get(city1) == null) {
				vertices.put(city1, G.insertVertex(city1));	// Insert city 1 in to the vertices of the graph
			}
			Vertex<String> v1 = vertices.get(city1);		// Vertex of first city
			
			// Check if city 2 exists yet
			if (vertices.get(city2) == null) {
				vertices.put(city2, G.insertVertex(city2));	// Insert city 2 in to the vertices of the graph
			}
			Vertex<String> v2 = vertices.get(city2);		// Vertex of second city
			
			G.insertEdge(v1, v2, cost);						// Add new edge to the graph
		}
	}
	
	/**
	 * Get city names in order of a depth-first search path traversal.
	 * 
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First Search algorithm. 
	 */
	public Iterable<String> DFSRoute(String departure, String destination) {
		Vertex<String> u = vertices.get(departure);				// Starting vertex
		Vertex<String> v = vertices.get(destination);			// End vertex
		
		Map<Vertex<String>, Edge<Integer>> forest = DFS(u);		// Get DFS forest map
		
		return constructPath(u, v, forest);						// Return the path of travel
	}
	
	/**
	 * Driver for recursive depth-first search path traversal
	 * 
	 * @param u - vertex to start DFS at
	 * @return map of the DFS path structure
	 */
	public Map<Vertex<String>, Edge<Integer>> DFS(Vertex<String> u) {
		Map<Vertex<String>, String> known = new HashMap<>();		 // Collection of known vertices
		Map<Vertex<String>, Edge<Integer>> forest = new HashMap<>(); // DFS map for path finding
		
		DFS(u, known, forest);										 // Construct DFS map
		
		return forest;
	}
	
	/**
	 * Recursive depth-first search of the graph
	 * 
	 * @param u - parent vertex to search from
	 * @param known - map of discovered vertices
	 * @param forest - current construction of paths
	 */
	private void DFS(Vertex<String> u, Map<Vertex<String>, String> known, Map<Vertex<String>, Edge<Integer>> forest) {
		known.put(u, ""); // Mark u as discovered
		
		// Find next edge to travel along
		for (Edge<Integer> e : sortedOutgoingEdges(u)) {
			Vertex<String> v = G.opposite(u, e); // Next potential vertex
			
			// Check if path to v has been explored yet
			if (known.get(v) == null) {
				forest.put(v, e);
				DFS(v, known, forest);
			}
		}
	}
	
	
	/**
	 * Get city names in order of a breadth-first search path traversal.
	 * 
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
     * @return Return the path from departure city to destination using Breadth First Search algorithm. 
	 */
	public Iterable<String> BFSRoute(String departure, String destination ) {
		Vertex<String> u = vertices.get(departure);				// Starting vertex
		Vertex<String> v = vertices.get(destination);			// End vertex
		
		Map<Vertex<String>, Edge<Integer>> forest = BFS(u);		// Get DFS forest map
		
		return constructPath(u, v, forest);						// Return the path of travel
	}
	
	/**
	 * Driver for breadth-first search of the graph
	 * 
	 * @param u - vertex to start BFS at
	 * @return forest map of the BFS path structure
	 */
	public Map<Vertex<String>, Edge<Integer>> BFS(Vertex<String> u) {
		Map<Vertex<String>, String> known = new HashMap<>();		 // Collection of known vertices
		Map<Vertex<String>, Edge<Integer>> forest = new HashMap<>(); // DFS map for path finding
		
		BFS(u, known, forest);										 // Construct DFS map
		
		return forest;
	}
	
	/**
	 * Iterative breadth-first search algorithm
	 * 
	 * @param u - parent vertex to search from
	 * @param known - map of discovered vertices
	 * @param forest - current construction of paths
	 */
	private void BFS(Vertex<String> s, Map<Vertex<String>, String> known, Map<Vertex<String>, Edge<Integer>> forest) {
		PositionalList<Vertex<String>> level = new DoublyLinkedList<>(); // Current exploring level
		known.put(s, "");	// Add initial vertex to known vertices
		level.addLast(s);	// Add starting node to levels
		
		// Find paths using BFS
		while(!level.isEmpty()) {
			PositionalList<Vertex<String>> nextLevel = new DoublyLinkedList<>(); // Next level to explore
			
			// Explore each outgoing edge of each vertex on the level
			for (Vertex<String> u : level) {
				for (Edge<Integer> e : sortedOutgoingEdges(u)) {
					Vertex<String> v = G.opposite(u, e);	// Potential discovery vertex
					
					// Discover vertex if not done already discovered
					if(known.get(v) == null) {
						known.put(v, "");
						forest.put(v, e);
						nextLevel.addLast(v);
					}
				}
			}
			
			level = nextLevel; // Move to next level
		}
	}
	
	/**
	 * Interface method for Dijkstra's algorithm based path finder
	 * 
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @param itinerary: an empty PositionalList object will be passed in to the method. 
	 * 	       When a shortest path is found, the city names in the path should be added to the list in the order. 
	 * @return return the cost of the shortest path from departure to destination. 
	 */
	public int DijkstraRoute(String departure, String destination, PositionalList<String> itinerary ) {
		Vertex<String> u = vertices.get(departure);					 // Starting vertex
		Vertex<String> v = vertices.get(destination);				 // End vertex
		
		Map<Vertex<String>, Edge<Integer>> forest = new HashMap<>(); // Minimum forest map
		Map<Vertex<String>, Integer> distanceMap  = new HashMap<>(); // Distance to each vertex

		Dijkstra(u, distanceMap, forest);							 // Get minimum distances and paths
		
		// Get the minimum positional path from starting vertex to end vertex
		for (String str : constructPath(u, v, forest)) {
			itinerary.addLast(str);
		}
		
		return distanceMap.get(v);
	}
	
	/**
	 * Dijkstra's algorithm minimum path finder
	 * 
	 * @param s	- Starting vertex to find minimum paths from
	 * @param distanceMap - empty map to store the minimum distance to each vertex
	 * @param forest - forest of minimum paths
	 */
	private void Dijkstra(Vertex<String> s, Map<Vertex<String>, Integer> distanceMap, Map<Vertex<String>, Edge<Integer>> forest) {
		AdaptablePriorityQueue<Integer, Vertex<String>> pq = new HeapAPQ<>();				// Distance APQ
		Map<Vertex<String>, Entry<Integer, Vertex<String>>> pqEntryMap = new HashMap<>();	// Entry map
		
		// Initialize the distance to each vertex
		for (Vertex<String> v : G.vertices()) {
			int d; // Current distance to a vertex

			if (v == s) {
				d = 0;
			} else {
				d = Integer.MAX_VALUE;
			}
			
			// Store distances and add vertex distance entries to APQ
			distanceMap.put(v, d);
			Entry<Integer, Vertex<String>> entry = pq.insert(d, v);
			pqEntryMap.put(v, entry);
		}
		
		// Populate the forest with the minimum paths
		while (!pq.isEmpty()) {
			// Get minimum distance entry and pull the data from the entry
			Entry<Integer, Vertex<String>> entry = pq.removeMin();
			int d = entry.getKey();
			Vertex<String> w = entry.getValue();
			
			// Update all connected vertices with new optimal distance
			for (Edge<Integer> e : sortedOutgoingEdges(w)) {
				Vertex<String> v = G.opposite(w, e);	// Connected vertex
				int newDistance = d + e.getElement();	// Updated distance
				
				// Check if updated distance is better than old distance
				if (newDistance < distanceMap.get(v)) {
					pq.replaceKey(pqEntryMap.get(v), 
							      newDistance);			// Update distance in APQ
					forest.put(v, e);					// Update optimal edge
					distanceMap.put(v, newDistance);	// Update minimum distance
				}
			}
		}
	}
	
	/**
	 * Get the names of the cities in traveling from vertex to another
	 * 
	 * @param u - starting vertex
	 * @param v - destination vertex
	 * @param forest - forest of path data
	 * @return iterable of the city names along the path
	 */
	public Iterable<String> constructPath(Vertex<String> u, Vertex<String> v, 
				  						 Map<Vertex<String>, Edge<Integer>> forest) {
		PositionalList<Edge<Integer>> path = new DoublyLinkedList<>(); // City path
		DoublyLinkedList<String> pathNames = new DoublyLinkedList<>(); // City names along the path
		
		// Check if vertex is connected to the starting vertex
		if (forest.get(v) != null) {
			Vertex<String> walk = v;			// Last vertex in the path
			pathNames.addFirst(v.getElement()); // Add name of last city in the path
			
			// Travel along the route backwards until we get to the starting node
			while (walk != u) {
				Edge<Integer> edge = forest.get(walk); // Backward travel edge
				path.addFirst(edge);				   // Add edge to path
				walk = G.opposite(walk, edge);		   // Move to previous vertex in the path
				pathNames.addFirst(walk.getElement()); // Add name to path
			}
		}
		
		return pathNames;
	}
	
	/**
	 * Given a vertex, get an sorted iterable list of the outgoing edges.
	 * The edges are sorted using a PQSort.
	 * 
	 * @param v: vertex v
	 * @return a list of edges ordered by edge's name
	 */
	public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v)  {
		AdaptablePriorityQueue<String, Edge<Integer>> Q = new HeapAPQ<String, Edge<Integer>>();	// Edge priority queue
		ArrayList<Edge<Integer>> sortedList = new ArrayList<Edge<Integer>>();					// Edge list
		
		// Add edges to priority queue
		for (Edge<Integer> e : G.outgoingEdges(v)) {
			Vertex<String> u = G.opposite(v, e);
			Q.insert(u.getElement(), e);
		}
		
		// Remove edges in order of their names onto the array list
		while (!Q.isEmpty()) {
			sortedList.addLast(Q.removeMin().getValue());
		}

		return sortedList;
	}
}
