package tv.rocketbeans.supermafiosi.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MafiosiGameContext {
	
	private final List<Mafiosi> candidates;
	
	private final Mafiosi player;
	
	private final DialogManager dialogManager;
	
	private final Map<String, Integer> bullets;
	
	public MafiosiGameContext(List<Mafiosi> candidates, Mafiosi player, DialogManager dialogManager) {
		this.candidates = candidates;
		this.player = player;
		this.dialogManager = dialogManager;
		this.bullets = new HashMap<String, Integer>();
	}

	public Mafiosi getPlayerMafiosi() {
		return player;
	}

	public List<Mafiosi> getCandidates() {
		return candidates;
	}
	
	public DialogManager getDialogManager() {
		return dialogManager;
	}
	
	public int getNumberOfBullets(String candidateName) {
		return bullets.get(candidateName);
	}
	
	public void addBullet(String candidateName) {
		Integer value = bullets.get(candidateName);
		if (bullets.get(candidateName) == null) {
			bullets.put(candidateName, 1);
		} else {
			bullets.put(candidateName, ++value);
		}
	}
}
