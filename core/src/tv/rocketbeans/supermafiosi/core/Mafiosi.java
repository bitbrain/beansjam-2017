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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mafiosi other = (Mafiosi) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mafiosi [name=" + name + ", bioId=" + bioId + ", age=" + age + ", spriteId=" + spriteId + ", avatarId="
				+ avatarId + "]";
	}
	
	
}
