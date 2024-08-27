package breadthFirstSearch;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * This class shows how to do a Breadth First Search in a Graph
 * Let’s consider n the number of nodes and c the number of connections of the graph. Then, in the worst 
 * case (being no node found), we might use addAll() and removeAll() methods to add and remove nodes up to the 
 * number of connections, giving us O(c) complexity for these operations. So, provided that c > n, the complexity of 
 * the overall algorithm will be O(c). Otherwise, it’ll be O(n). This is generally noted O(n + c), which can be 
 * interpreted as a complexity depending on the greatest number between n and c.
 * 
 * @author bgoff
 */
public class Node<T> 
{
	private T value;
	private Set<Node<T>> neighbors;

	public Node(T value) 
	{
		this.value = value;
		this.neighbors = new HashSet<>();
	}

	public T getValue() 
	{
		return value;
	}

	public Set<Node<T>> getNeighbors() 
	{
		return Collections.unmodifiableSet(neighbors);
	}

	public void connect(Node<T> node) 
	{
		if (this == node)
			throw new IllegalArgumentException("Can't connect node to itself");
		this.neighbors.add(node);
		node.neighbors.add(this);
	}

	public static <T> Optional<Node<T>> search(T value, Node<T> start) 
	{
		Queue<Node<T>> queue = new ArrayDeque<>();
		queue.add(start);

		Node<T> currentNode;
		Set<Node<T>> alreadyVisited = new HashSet<>();

		while (!queue.isEmpty()) 
		{
			currentNode = queue.remove();
			System.out.println("Visited node with value: " + currentNode.getValue());

			if (currentNode.getValue().equals(value))
				return Optional.of(currentNode);
			else 
			{
				alreadyVisited.add(currentNode);
				queue.addAll(currentNode.getNeighbors());
				queue.removeAll(alreadyVisited);
			}
		}
		return Optional.empty();
	}

	public static void main(String[] args) {
		Node<Integer> start = new Node<>(10);
		Node<Integer> firstNeighbor = new Node<>(2);
		start.connect(firstNeighbor);

		Node<Integer> firstNeighborNeighbor = new Node<>(3);
		firstNeighbor.connect(firstNeighborNeighbor);
		firstNeighborNeighbor.connect(start);

		Node<Integer> secondNeighbor = new Node<>(4);
		start.connect(secondNeighbor);
		
		int findInt = 4;
		System.out.println("Searched the following to find " + findInt);
		search(findInt, firstNeighborNeighbor);
		
		findInt = 3;
		System.out.println("\n\nSearched the following to find " + findInt);
		search(findInt, start);

	}

}
