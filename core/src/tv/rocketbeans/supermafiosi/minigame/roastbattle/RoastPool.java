package tv.rocketbeans.supermafiosi.minigame.roastbattle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.roastbattle.Roast.Type;

// Contains some pre-defined roasts
public class RoastPool {
	
	private final Map<Roast.Type, List<Roast>> roastMap = new HashMap<Roast.Type, List<Roast>>();

	public RoastPool() {
		initialiseRoasts();
	}
	
	public Roast getRandomRoast(Roast.Type type) {
		List<Roast> roasts = roastMap.get(type);
		if (roasts != null) {
			return roasts.get((int) (Math.random() * (float)roasts.size()));
		}
		return null;
	}

	public Roast getRandomRoast() {
		Roast.Type type = Roast.Type.values()[(int) (Math.random() * Roast.Type.values().length)];
		return getRandomRoast(type);
	}
	
	private void initialiseRoasts() {
		// SCISSORS
		List<Roast> scissors = new ArrayList<Roast>();
		scissors.add(new Roast(Type.SCISSORS, Message.MINIGAME_ROAST_SCISSOR_1));
		scissors.add(new Roast(Type.SCISSORS, Message.MINIGAME_ROAST_SCISSOR_2));
		scissors.add(new Roast(Type.SCISSORS, Message.MINIGAME_ROAST_SCISSOR_3));
		scissors.add(new Roast(Type.SCISSORS, Message.MINIGAME_ROAST_SCISSOR_4));
		scissors.add(new Roast(Type.SCISSORS, Message.MINIGAME_ROAST_SCISSOR_5));
		scissors.add(new Roast(Type.SCISSORS, Message.MINIGAME_ROAST_SCISSOR_6));
		
		// ROCKS
		List<Roast> rocks = new ArrayList<Roast>();
		rocks.add(new Roast(Type.ROCK, Message.MINIGAME_ROAST_ROCK_1));
		rocks.add(new Roast(Type.ROCK, Message.MINIGAME_ROAST_ROCK_2));
		rocks.add(new Roast(Type.ROCK, Message.MINIGAME_ROAST_ROCK_3));
		rocks.add(new Roast(Type.ROCK, Message.MINIGAME_ROAST_ROCK_4));
		rocks.add(new Roast(Type.ROCK, Message.MINIGAME_ROAST_ROCK_5));
		rocks.add(new Roast(Type.ROCK, Message.MINIGAME_ROAST_ROCK_6));
		
		// PAPERS
		List<Roast> papers = new ArrayList<Roast>();
		papers.add(new Roast(Type.PAPER, Message.MINIGAME_ROAST_PAPER_1));
		papers.add(new Roast(Type.PAPER, Message.MINIGAME_ROAST_PAPER_2));
		papers.add(new Roast(Type.PAPER, Message.MINIGAME_ROAST_PAPER_3));
		papers.add(new Roast(Type.PAPER, Message.MINIGAME_ROAST_PAPER_4));
		papers.add(new Roast(Type.PAPER, Message.MINIGAME_ROAST_PAPER_5));
		papers.add(new Roast(Type.PAPER, Message.MINIGAME_ROAST_PAPER_6));
		
		roastMap.put(Roast.Type.PAPER, papers);
		roastMap.put(Roast.Type.SCISSORS, scissors);
		roastMap.put(Roast.Type.ROCK, rocks);
	}
}
