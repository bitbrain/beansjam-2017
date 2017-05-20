package tv.rocketbeans.supermafiosi.minigame.roastbattle;

import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.ui.MultiSelect.IconProvider;

public class RoastIconProvider implements IconProvider<Roast> {

	@Override
	public String getIconPath(Roast type) {
		if (type.getType().equals(Roast.Type.SCISSORS)) {
			return Asset.Textures.MINIGAME_ROASTBATTLE_ICON_SKULL; 
		}
		if (type.getType().equals(Roast.Type.ROCK)) {
			return Asset.Textures.MINIGAME_ROASTBATTLE_ICON_HEART; 
		}
		if (type.getType().equals(Roast.Type.PAPER)) {
			return Asset.Textures.MINIGAME_ROASTBATTLE_ICON_RAINDROP; 
		}
		return null;
	}

}
