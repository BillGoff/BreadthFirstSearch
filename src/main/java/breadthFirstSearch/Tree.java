package breadthFirstSearch;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * This class shows how to do a Breadth First Search in a java Tree (Also known as a Graph).
 * That concludes the case of trees. Let’s now see how to deal with graphs. Contrarily to trees, graphs can contain 
 * cycles. That means, as we’ve seen in the previous section, we have to remember the nodes we visited to avoid an 
 * infinite loop. We’ll see in a moment how to update the algorithm to consider this problem, but first, let’s 
 * define our graph structure:
 * 
 * @author bgoff
 *
 * @param <T>
 */
public class Tree<T> 
{
	private T value;
	private List<Tree<T>> children;

	private Tree(T value) 
	{
		this.value = value;
		this.children = new ArrayList<>();
	}

	public static <T> Tree<T> of(T value) 
	{
		return new Tree<>(value);
	}

	public T getValue() 
	{
		return value;
	}

	public List<Tree<T>> getChildren() 
	{
		return Collections.unmodifiableList(children);
	}

	public Tree<T> addChild(T value) 
	{
		Tree<T> newChild = new Tree<>(value);
		children.add(newChild);
		return newChild;
	}
	
	public static <T> Optional<Tree<T>> search(T value, Tree<T> root) 
	{
		Queue<Tree<T>> queue = new ArrayDeque<>();
		queue.add(root);

		Tree<T> currentNode;
		while (!queue.isEmpty()) 
		{
			currentNode = queue.remove();
			System.out.println("Visited node : " + currentNode.getValue());

			if (currentNode.getValue().equals(value))
				return Optional.of(currentNode);
			else 
				queue.addAll(currentNode.getChildren());
		}
        return Optional.empty();
    }

	/* Driver program to test above functions */
	public static void main(String[] args) 
	{
		Tree<Integer> root = Tree.of(10);
		Tree<Integer> rootFirstChild = root.addChild(2);
		Tree<Integer> depthMostChild = rootFirstChild.addChild(3);
		Tree<Integer> rootSecondChild = root.addChild(4);
		
		int findInt = 4;
		System.out.println("Searched the following to find " + findInt);
		search(findInt, root);
		
		findInt = 3;
		System.out.println("\n\nSearched the following to find " + findInt);
		search(3, root);
	}
}
