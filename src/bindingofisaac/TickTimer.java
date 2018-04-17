package bindingofisaac;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import javafx.event.EventHandler;

import java.util.ArrayList;

/**
 * Represents a collection of ticks, objects that run for a specified time
 * for a specified amount of loops (cycles).
 *
 * Each tick is a KeyFrame wrapped in a Timeline. Ticks are collected into
 * an ArrayList. In this implementation, each tick only consists of one
 * repeating KeyFrame.
 *
 * Individual ticks can be started, stopped, paused, restarted, or their
 * cycle count changed. All ticks in the collection can be started, stopped,
 * restarted, or removed.
 *
 * @author Nolan
 * @version 20180314-1
 */

public class TickTimer {

	private ArrayList<Timeline> ticks;
	private Timeline mainTimeline;
	private int currentTickIndex;

	public TickTimer() {
		ticks = new ArrayList<>();
		mainTimeline = new Timeline();
	}

	/**
	 * Creates a tick and adds it to the ticks list.
	 * The tick does NOT play immediately; instead, the
	 * play() method must be called using the returned
	 * index. If the tick should be played immediately,
	 * addTickAndPlay() should be used for simplicity.
	 * @param duration the duration in milliseconds the tick should last, per loop (cycle)
	 * @param cycleCount the amount of times the tick should play before finishing
	 *                   (note that Timeline.INDEFINITE is acceptable here)
	 * @param onTickEvent the EventHandler that should run each tick. Per standard format,
	 *                    this should be an ActionEvent EventHandler, and must implement the
	 *                    handle(ActionEvent event) method
	 * @return the index of this tick in the ticks ArrayList. This is necessary if you want to
	 *         play, pause, or stop this individual tick later on. If you do not capture this
	 *         value, it is still possible to interact with the tick using playAll() and stopAll().
	 */
	public int addTick(int duration, int cycleCount, EventHandler<ActionEvent> onTickEvent) {
		KeyFrame cKeyFrame = new KeyFrame(Duration.millis(duration), onTickEvent);
		Timeline cTimeline = new Timeline();
		cTimeline.setCycleCount(cycleCount);
		cTimeline.getKeyFrames().add(currentTickIndex, cKeyFrame);
		ticks.add(cTimeline);

		int tIndexTemp = currentTickIndex;
		currentTickIndex++;
		return tIndexTemp;
	}

	/**
	 * Creates a tick and adds it to the ticks list.
	 * The tick will play immediately upon adding.
	 * @param duration the duration in milliseconds the tick should last, per loop (cycle)
	 * @param cycleCount the amount of times the tick should play before finishing
	 *                   (note that Timeline.INDEFINITE is acceptable here)
	 * @param onTickEvent the EventHandler that should run each tick. Per standard format,
	 *                    this should be an ActionEvent EventHandler, and must implement the
	 *                    handle(ActionEvent event) method
	 * @return the index of this tick in the ticks list. This is necessary if you want to
	 *         play, pause, or stop this individual tick later on. If you do not capture this
	 *         value, it is still possible to interact with the tick using playAll() and stopAll().
	 */
	public int addTickAndPlay(int duration, int cycleCount, EventHandler<ActionEvent> onTickEvent) {
		int tick = addTick(duration, cycleCount, onTickEvent);
		play(tick);
		return tick;
	}

	/**
	 * Removes and stops the tick at the passed index.
	 * @param index the tick to remove
	 */
	public void removeTick(int index) {
		stop(index);
		ticks.remove(index);
	}

	/**
	 * Removes and stops all ticks from the ticks list.
	 */
	public void removeAllTicks() {
		stopAll();
		ticks.removeAll(ticks);
		currentTickIndex = 0;
	}

	/**
	 * Plays the tick at the passed index, or resumes it from paused playback.
	 * @param index the index of the tick to play
	 */
	public void play(int index) {
		ticks.get(index).play();
	}

	/**
	 * Plays the tick at the specified index from the start, then removes it once playback
	 * has finished.
	 * @param index the index of the tick to play once
	 */
	public void playOnce(int index) {
		ticks.get(index).playFromStart();
		while (ticks.get(index).getStatus() == Animation.Status.RUNNING) {
			// do nothing, just wait until it stops
		}
		removeTick(index);
	}

	/**
	 * Pauses playback of the tick at the passed index. Calling play() will resume playback.
	 * @param index the index of the tick to pause
	 */
	public void pause(int index) {
		ticks.get(index).pause();
	}

	/**
	 * Stops playback of the tick at the passed index. Calling play() will play from the start.
	 * @param index the index of the tick to stop
	 */
	public void stop(int index) {
		ticks.get(index).stop();
	}

	/**
	 * Restarts playback of the tick at the passed index so that the tick plays from the start.
	 * If the tick is stopped, this is synonymous with play().
	 * @param index the index of the tick to restart
	 */
	public void restart(int index) {
		ticks.get(index).playFromStart();
	}

	/**
	 * Changes the cycle count of the tick at the passed index to the passed cycle count.
	 * @param index the tick to change cycle count of
	 * @param count the new cycle count
	 */
	public void changeCycleCount(int index, int count) {
		ticks.get(index).setCycleCount(count);
	}

	/**
	 * Plays all ticks in the ticks list.
	 * If a tick is currently playing, nothing will occur to that tick.
	 * If a tick is currently paused, it will resume playback.
	 * If a tick is currently stopped, it will restart playback.
	 */
	public void playAll() {
		for (Timeline tick : ticks) {
			tick.play();
		}
	}

	/**
	 * Stops playback of all ticks in the ticks list.
	 * Calling playAll() or play() on a tick will play from the start.
	 */
	public void stopAll() {
		for (Timeline tick : ticks) {
			tick.stop();
		}
	}

	/**
	 * Restarts playback of all ticks in the ticks list.
	 * All ticks will play from the start.
	 */
	public void restartAll() {
		for (Timeline tick : ticks) {
			tick.playFromStart();
		}
	}

}
