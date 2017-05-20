package tv.rocketbeans.supermafiosi.minigame.roastbattle;

/**
 * A "roast" is a punch line to tell someone how bad he is.
 * In the Mafia world this is mandatory to demonstrate dominance!
 */
public class Roast implements Comparable<Roast> {

	public enum Type {
		
		ROCK("PAPER", "SCISSORS"), PAPER("SCISSORS", "ROCK"), SCISSORS("ROCK", "PAPER");
		
		private String losesTo, winsAgainst;
		
		Type(String losesTo, String winsAgainst) {
			this.losesTo = losesTo;
			this.winsAgainst = winsAgainst;
		}
		
		public boolean winsAgainst(Type otherType) {
			Type winsAgainstType = Type.valueOf(winsAgainst);
			return winsAgainstType.equals(otherType);
		}
		
		public boolean losesTo(Type otherType) {
			Type losesToType = Type.valueOf(losesTo);
			return losesToType.equals(otherType);
		}
	}
	
	private final Type type;
	
	private final String messageKey;
	
	public Roast(Type type, String messageKey) {
		this.type = type;
		this.messageKey = messageKey;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getMessageKey() {
		return messageKey;
	}

	@Override
	public int compareTo(Roast other) {
		// 1  = win
		// 0  = draw
		// -1 = lose
		// Rock beats scissors
		// Paper beats rock
		// Scissor beats paper
		if (type.winsAgainst(other.getType())) {
			return 1;
		}
		if (type.losesTo(other.getType())) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Roast [type=" + type + "]";
	}
	
	
}
