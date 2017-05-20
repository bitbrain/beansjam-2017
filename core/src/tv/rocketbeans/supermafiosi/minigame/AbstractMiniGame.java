package tv.rocketbeans.supermafiosi.minigame;

import java.util.HashSet;
import java.util.Set;

abstract class AbstractMiniGame implements MiniGame {
	
	protected final Set<MiniGameListener> listeners = new HashSet<MiniGameListener>();

	@Override
	public void addListener(MiniGameListener listener) {
		listeners.add(listener);
	}
	
	protected void notifyComplete(MiniGameResult result) {
		for (MiniGameListener listener : listeners) {
			listener.onComplete(this, result);
		}
	}
}
