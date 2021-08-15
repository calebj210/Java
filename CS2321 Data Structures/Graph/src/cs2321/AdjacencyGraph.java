package cs2321;

import net.datastructures.*;

/*
 * Adjacency MAP implementation of an Adjacency Graph
 * 
 * @author Caleb Jacobs (cajacobs)
 * Date last modified: 04/14/2021
 * Course: CS2321 Spring 2021
 */
public class AdjacencyGraph<V, E> implements Graph<V, E> {
	/**
	 * Vertex definition of a graph
	 * 
	 * @param <V> - vertex element type
	 */
	private class InnerVertex<V> implements Vertex<V> {
		private V element;									// Element of vertex
		private Position<Vertex<V>> pos;					// Vertex position
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;	// Outgoing and incoming edge maps

		/**
		 * Constructor for creating a new vertex
		 * 
		 * @param element - element of the new vertex
		 * @param graphIsDirected - Check if vertex is part of a directed graph
		 */
		public InnerVertex(V element, boolean graphIsDirected) {
			this.element = element;					// Element of vertex
			outgoing = new HashMap<>();				// Outgoing edge map

			if (graphIsDirected) {
				incoming = new HashMap<>();			// Keep track of out and in separately
			} else {
				incoming = outgoing;				// Save space in storing directions
			}
		}
		
		/**
		 * Get the element of the vertex
		 * 
		 * @return the element of the vertex
		 */
		public V getElement() {
			return element;
		}
		
		/**
		 * Set the element of the vertex
		 * 
		 * @param element to set to
		 */
		public void setElement(V element) {
			this.element = element;
		}
		
		/**
		 * Set the position of the vertex
		 * 
		 * @param p - position to set the vertex to
		 */
		public void setPosition(Position<Vertex<V>> p) {
			pos = p;
		}
		
		/**
		 * Get the position of a given vertex
		 * 
		 * @return the position of the vertex
		 */
		public Position<Vertex<V>> getPosition() {
			return pos;
		}
		
		/**
		 * Get the outgoing edges map
		 * 
		 * @return the outgoing edges map
		 */
		public Map<Vertex<V>, Edge<E>> getOutgoing() {
			return outgoing;
		}

		/**
		 * Get the incoming edges map
		 * 
		 * @return the incoming edges map
		 */
		public Map<Vertex<V>, Edge<E>> getIncoming() {
			return incoming;
		}
		
		/**
		 * Validate that the current vertex belongs to the parent graph
		 * 
		 * @param graph to check if vertex is in
		 * @return true if vertex is in the given graph
		 */
		public boolean validate(Graph<V, E> graph) {
			return (AdjacencyGraph.this == graph && pos != null);
		}
	}
	
	/**
	 * Edge definition of a graph
	 * 
	 * @param <E> - edge element type
	 */
	private class InnerEdge<E> implements Edge<E> {
		private E element;							// Element of the edge
		private Position<Edge<E>> pos;				// Position of the edge
		private Vertex<V>[] endpoints;				// List of endpoints vertices of the edge
		
		/**
		 * Construct a edge with the given endpoints
		 * 
		 * @param u	- first endpoint
		 * @param v - last endpoint
		 * @param element - element of the edge
		 */
		public InnerEdge(Vertex<V> u, Vertex<V> v, E element) {
			this.element = element;
			endpoints = (Vertex<V>[]) new Vertex[] {u, v};
		}
		
		/**
		 * Get the element of the edge
		 * 
		 * @return the element of the edge
		 */
		public E getElement() {
			return element;
		}
		
		/**
		 * Set the element of the edge
		 * 
		 * @param element to set to
		 */
		public void setElement(E element) {
			this.element = element;
		}
		
		/**
		 * Get the endpoints of the edge
		 * 
		 * @return array of the endpoints of the edge
		 */
		public Vertex<V>[] getEndpoints() {
			return endpoints;
		}
		
		/**
		 * Set the position of the edge
		 * 
		 * @param p - position to set the edge to
		 */
		public void setPosition(Position<Edge<E>> p) {
			pos = p;
		}
		
		/**
		 * Get the position of a given edge
		 * 
		 * @return the position of the edge
		 */
		public Position<Edge<E>> getPosition() {
			return pos;
		}
		
		/**
		 * Validate that the current edge is in the graph
		 * 
		 * @param graph to check if edge is in
		 * @return true if this edge is in the graph
		 */
		public boolean validate(Graph<V, E> graph) {
			return AdjacencyGraph.this == graph && pos != null;
		}
	}
	
	private boolean isDirected;												// Directed check
	private PositionalList<Vertex<V>> vertices = new DoublyLinkedList<>();	// List of vertices
	private PositionalList<Edge<E>> edges      = new DoublyLinkedList<>();	// List of edges
	
	/**
	 * Mark if the new graph is directed or not
	 * 
	 * @param directed - is the graph directed?
	 */
	public AdjacencyGraph(boolean directed) {
		isDirected = directed;
	}

	/**
	 * Default graph to being undirected
	 */
	public AdjacencyGraph() {
		this(false);
	}

	/**
	 * Get the edges in a graph
	 * 
	 * @return an iterable of the edges in a graph
	 */
	@TimeComplexity("O(m)")
	public Iterable<Edge<E>> edges() {
		/* TCJ
		 * Just as vertices(), to iterate through each of
		 * the edges, we need to obtain the list of edges
		 * which takes O(1) and then scan through each
		 * of the m edges which obviously takes O(m) time.
		 */
		return edges;
	}

	/**
	 * Get the end vertices of a given edge
	 * 
	 * @param e - edge to get the endpoints of
	 * @return an array of the endpoints
	 */
	@TimeComplexity("O(1)")
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		/* TCJ
		 * To get the endpoints of an edge, we simply
		 * read the endpoint data stored in the edge
		 * and nothing more. The reading of the vertice
		 * data takes O(1) time.
		 */
		InnerEdge<E> edge = validate(e);
		return edge.getEndpoints();
	}


	/**
	 * Insert an edge into the graph
	 * 
	 * @param u - first endpoint of new edge
	 * @param v - second endpoint of new edge
	 * @param o - element of the new edge
	 * @return the inserted edge
	 */
	@TimeComplexity("O(min(deg(u), deg(v))")
	@TimeComplexityExpected("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		/* TCJ
		 * The cost is dictated by getEdge method which has
		 * a worst case of O(min(deg(u), deg(v))) and an
		 * expected case of O(1). See getEdge for more TCJ.
		 */
		if (getEdge(u, v) == null) {
			InnerEdge<E> e = new InnerEdge<>(u, v, o);	// New edge
			e.setPosition(edges.addLast(e));			// Update position of new edge
			InnerVertex<V> first = validate(u);			// Validate first vertex
			InnerVertex<V> second = validate(v);		// Validate second vertex
			first.getOutgoing().put(v, e);				// Add first vertex to outgoing edge list
			second.getIncoming().put(u, e);				// Add second vertex to incoming edge list
			return e;
		} else {
			throw new IllegalArgumentException("Edge from u to v exists");
		}
	}

	/**
	 * Insert a vertex into the graph
	 * 
	 * @param o - element of the new vertex
	 * @return the new vertex
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		/* TCJ
		 * To add a new vertex, we simply need to create a new vertex
		 * which takes O(1) and then add it to the end of our linked
		 * list which takes O(1) due to us knowing where the position
		 * of the last node in the list is.
		 */
		InnerVertex<V> v = new InnerVertex<>(o, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}

	/**
	 * Return the number of edges in a graph
	 * 
	 * @return number of edges
	 */
	@TimeComplexity("O(1)")
	public int numEdges() {
		/* TCJ
		 * Simply put, we the linked list storing edges keeps
		 * track of the number of nodes in the list. So,
		 * getting the size of the edges list takes O(1).
		 */
		return edges.size();
	}

	/**
	 * Get the number of vertices in a graph
	 * 
	 * @return the number of vertices in a graph
	 */
	@TimeComplexity("O(1)")
	public int numVertices() {
		return vertices.size();
	}

	/**
	 * Get the vertex that is on the other end of the edge
	 * 
	 * @param v - vertex to get the opposite of
	 * @param e - edge to scan across for the opposite vertex
	 * @return the opposite vertex
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);				// Validate the edge
		Vertex<V>[] endpoints = edge.getEndpoints();	// Endpoints of the edge
		
		if (endpoints[0] == v) {
			return endpoints[1];
		} else if (endpoints[1] == v) {
			return endpoints[0];
		} else {
			throw new IllegalArgumentException("v is not an endpoint of this edge");
		}
	}

	/**
	 * Remove an edge from the graph
	 * 
	 * @param e - edge to remove
	 * @throws IllegalArgumentException if e is an invalid edge
	 */
	@TimeComplexity("O(deg(u) + deg(v))")
	@TimeComplexityExpected("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		/* TCJ
		 * To understand the expected time complexity, note that the longest
		 * step in removing an edge is removing the edge from the incoming
		 * and outgoing edge hashmaps of the endpoint vertices. To remove
		 * the edge from the different hashmaps, we need to find the edge 
		 * in the hashmaps which takes, on average, O(1) time. The worst case
		 * comes in when the hashmaps above have many collisions. In that case
		 * we would need to do a basic search through the different hashmaps
		 * to find the edge which would take O(deg(v) + deg(u)) where u and v
		 * are the endpoint vertices on the given edge.
		 */
		InnerEdge<E> edge = validate(e);				// Validate the edge
		
		// Get the endpoints of the edge
		InnerVertex<V>[] verts = new InnerVertex[2];
		for (int i = 0; i < 2; i++) {
			verts[i] = (InnerVertex<V>) edge.getEndpoints()[i];
		}
		
		// Remove edge from incoming and outgoing lists
		verts[0].getOutgoing().remove(verts[1]);
		verts[1].getIncoming().remove(verts[0]);
		
		edges.remove(edge.getPosition());				// Remove edge from total edge list
		edge.setPosition(null);							// Safeguard edge position
	}

	/**
	 * Remove a given vertex from the graph
	 * 
	 * @param v- vertex to remove
	 * @throws IllegalArgumentException if v is an invalid vertex
	 */
	@TimeComplexity("O(deg(v))")
	@TimeComplexityExpected("O(deg(v))")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		/* TCJ
		 * To remove a vertex, we need to remove every incoming
		 * and outgoing edge to v. To do this, we need to iterate
		 * over every incoming and outgoing edge to v which has 
		 * deg(v) of those edges. Thus, the worst step is iterating 
		 * and removing each of these deg(v) edges and so the worst
		 * case time complexity is O(deg(v)).
		 */
		InnerVertex<V> vert = validate(v);				// Validate vertex
		
		// Remove outgoing edges
		for (Edge<E> e : vert.getOutgoing().values()) {
			removeEdge(e);
		}
		
		// Remove incoming edges
		for(Edge<E> e : vert.getIncoming().values()) {
			removeEdge(e);
		}
		
		vertices.remove(vert.getPosition());
	}

	/** 
     * replace the element in edge object, return the old element
     * 
     * @param e - edge to replace element of
     * @param o - new element of edge
     * @return the old element of the edge
     * @throws IllegalArgumentException if the edge is invalid
     */
	@TimeComplexity("O(1)")
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		InnerEdge<E> edge = validate(e);		// Validate edge
		E old = edge.getElement();				// Get old element
		edge.setElement(o);						// Update element

		return old;
	}

	/** 
     * replace the element in vertex object, return the old element
     * 
     * @param e - vertex to replace element of
     * @param o - new element of vertex
     * @return the old element of the vertex
     * @throws IllegalArgumentException if the vertex is invalid
     */
	@TimeComplexity("O(1)")
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);		// Validate edge
		V old = vert.getElement();				// Get old element
		vert.setElement(o);						// Update element

		return old;
	}

	/**
	 * Get all of the vertices the graph
	 * 
	 * @return an iterable of the vertices in the graph
	 */
	@TimeComplexity("O(n)")
	public Iterable<Vertex<V>> vertices() {
		/* TCJ
		 * Getting the list of vertices is O(1) but to iterate through
		 * each vertice, we would need to scan through the list of n
		 * vertices and so the time to iterate through the list is O(n).
		 */
		return vertices;
	}

	/**
	 * Get the number of outgoing edges to a vertex
	 * 
	 * @param v - vertex to get the outgoing edges to
	 * @return number of outgoing edges
	 */
	@Override
	@TimeComplexity("O(1)")
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);		// Validate vertex
		
		return vert.getOutgoing().size();
	}

	/**
	 * Get the number of incoming edges to a vertex
	 * 
	 * @param v - vertex to get the incoming edges to
	 * @return number of incoming edges
	 */
	@Override
	@TimeComplexity("O(1)")
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex<V> vert = validate(v);

		return vert.getIncoming().size();
	}

	/**
	 * Get all of the outgoing edges to a vertex
	 * 
	 * @param v - vertex to get outgoing edges to
	 * @return iterable of the outgoing edges
	 */
	@Override
	@TimeComplexity("O(deg(v))")
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		/* TCJ
		 * To iterate through the outgoing edges of a vertex
		 * we need to obtain the list of outgoing edges which 
		 * takes O(1). Once we have the list, we need to scan
		 * through each deg(v) edges in the list and so 
		 * iterating through the edges takes O(deg(v)) time.
		 */
		InnerVertex<V> vert = validate(v);		// Validate vertex

		return vert.getOutgoing().values();
	}

	/**
	 * Get all of the incoming edges to a vertex
	 * 
	 * @param v - vertex to get incoming edges to
	 * @return iterable of the incoming edges
	 */
	@Override
	@TimeComplexity("O(deg(v))")
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		/* TCJ
		 * See time complexity of outgoing edges.
		 */
		InnerVertex<V> vert = validate(v);		// Validate vertex

		return vert.getIncoming().values();
	}

	/**
	 * Get the edge between two vertices
	 * 
	 * @param u - first endpoint
	 * @param v - second endpoint
	 * @return edge between u and v
	 */
	@Override
	@TimeComplexity("O(min(deg(u), deg(v))")
	@TimeComplexityExpected("O(1)")
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		/* TCJ
		 * In the worst case, our hashmaps of the outgoing and 
		 * incoming edges have many collisions and so we would
		 * need to scan through each edge individually until
		 * we found the edge. The scanning through each edge
		 * takes O(min(deg(u), deg(v))) time because we would
		 * scan the smaller edge list between u and v. Yet,
		 * on average our hashmap will have few collisions and
		 * so we can jump right to the edge in the hashmap which
		 * takes O(1) time to do.
		 */
		InnerVertex<V> first = validate(u);		// Validate first index
		return first.getOutgoing().get(v);		// Find edge to second vertex
	}
	
	/**
	 * Validate that the given vertex is valid and in the graph
	 * 
	 * @param v	- vertex to validate
	 * @return valid vertex
	 */
	@TimeComplexity("O(1)")
	private InnerVertex<V> validate(Vertex<V> v) {
		// Check if v is a type of vertex in our graph
		if (!(v instanceof InnerVertex)) {
			throw new IllegalArgumentException("Invalid vertex");
		}
		
		InnerVertex<V> vert = (InnerVertex<V>) v;		// Make vertex usable

		// Check if vertex is in the graph
		if (!vert.validate(this)) {
			throw new IllegalArgumentException("Invalid vertex");
		}
		
		return vert;
	}
	
	/**
	 * Validate that the given edge is valid and in the graph
	 * 
	 * @param e	- edge to validate
	 * @return valid edge
	 */
	@TimeComplexity("O(1)")
	private InnerEdge<E> validate(Edge<E> e) {
		// Check if e is a usSable type of edge
		if (!(e instanceof InnerEdge)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		
		InnerEdge<E> edge = (InnerEdge<E>) e;			// Make edge usable
		
		// Check if edge is in the graph
		if (!edge.validate(this)) {
			throw new IllegalArgumentException("Invalid edge");
		}
		
		return edge;
	}
}
