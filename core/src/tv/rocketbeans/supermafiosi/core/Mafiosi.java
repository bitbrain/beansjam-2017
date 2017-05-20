package tv.rocketbeans.supermafiosi.core;

// an evil mafiosi who could be a potential Don
public class Mafiosi {

	private final String name;		
	private final String bioId;	
	private final int age;
	private final String spriteId, avatarId;
	
	public Mafiosi(String name, String bioId, int age, String spriteId, String avatarId) {
		this.name = name;
		this.bioId = bioId;
		this.age = age;
		this.spriteId = spriteId;
		this.avatarId = avatarId;
	}

	public String getName() {
		return name;
	}

	public String getBioId() {
		return bioId;
	}

	public int getAge() {
		return age;
	}

	public String getSpriteId() {
		return spriteId;
	}

	public String getAvatarId() {
		return avatarId;
	}
}
