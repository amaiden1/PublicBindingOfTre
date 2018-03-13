package bindingofisaac;

import javafx.scene.Node;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SynchronizedUpdate {

	private Node newNode;
	private Node oldNode;

	/**
	 * Constructor for SynchronizedUpdate.
	 * @param currentNode The current state of the Node. This is used
	 *                to determine exactly which Node should be updated.
	 * @param updatedNode The Node with desired updates or changes made.
	 *                    This will replace the oldNode.
	 */
	public SynchronizedUpdate(Node currentNode, Node updatedNode) {
		this.oldNode = currentNode;
		this.newNode = updatedNode;
	}

	/**
	 * Returns the Node which to update.
	 * @return the Node that is being updated
	 */
	public Node getCurrentNode() {
		return oldNode;
	}

	/**
	 * Returns the updated state of the Node.
	 * @return the Node with desired updates
	 */
	public Node getUpdatedNode() {
		return newNode;
	}

}