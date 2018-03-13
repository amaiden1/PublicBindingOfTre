package bindingofisaac;

import javafx.scene.Node;
import java.util.*;

public class SynchronizedUpdateThread {

	private PriorityQueue<SynchronizedUpdate> updateQueue;

	/**
	 * Constructor for SynchronizedUpdateThread.
	 * @param initialUpdates Any initial updates to add to the queue.
	 *                       They will be added in the order passed, meaning
	 *                       the last update will be at the rear of the queue
	 *                       and thus executed last.
	 */
	public SynchronizedUpdateThread(SynchronizedUpdate... initialUpdates) {
		updateQueue = new PriorityQueue<>();
		push(initialUpdates);
	}

	/**
	 * Alternate constructor for SynchronizedUpdateThread.
	 * This constructor simply initializes the queue and does not
	 * take any initial items to add.
	 */
	public SynchronizedUpdateThread() {
		updateQueue = new PriorityQueue<>();
	}

	/**
	 * Returns the update at the front of the queue
	 * without removing it.
	 * @return the next update that will be processed
	 */
	public SynchronizedUpdate getNext() {
		return updateQueue.peek();
	}

	/**
	 * Removes and returns the update at the front of
	 * the queue. Similar to pop() for stacks.
	 * @return the next update to process
	 */
	public SynchronizedUpdate popNext() {
		return updateQueue.poll();
	}

	/**
	 * Adds the passed update to the rear of the queue.
	 * @param update the update to push
	 */
	public void push(SynchronizedUpdate update) {
		updateQueue.add(update);
	}

	/**
	 * Adds all passed updates to the rear of the queue.
	 * They will be added in the order passed, meaning
	 * the last update will be at the rear of the queue
	 * and thus executed last.
	 * @param updates updates to push
	 */
	public void push(SynchronizedUpdate... updates) {
		for (SynchronizedUpdate update : updates) {
			updateQueue.add(update);
		}
	}

	/**
	 * Removes all updates from the queue.
	 */
	public void flush() {
		updateQueue.removeAll(updateQueue);
	}

	/**
	 * Returns the amount of updates remaining, the size
	 * of the queue.
	 * @return amount of updates in the queue
	 */
	public int getUpdatesRemaining() {
		return updateQueue.size();
	}

	/**
	 * Returns if the queue is empty. This is used when processing
	 * each item in the queue, to ensure a null item is not processed.
	 * @return false if the queue has items remaining, true if empty
	 */
	public boolean isEmpty() {
		return updateQueue.isEmpty();
	}



}
