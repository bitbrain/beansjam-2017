package tv.rocketbeans.supermafiosi.core;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputAdapter;

import tv.rocketbeans.supermafiosi.i18n.Bundle;

public class DialogManager extends InputAdapter {
	
	private List<Dialog> dialogs = new ArrayList<Dialog>();
	
	private Dialog currentDialog;

	public void addDialog(String title, String dialogKey, String avatarKey) {
		dialogs.add(new Dialog(title, Bundle.translations.get(dialogKey), avatarKey));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return nextDialog();
	}
	
	public boolean nextDialog() {
		if (dialogs.size() > 0) {
			currentDialog = dialogs.remove(0);
			return true;
		} else {
			currentDialog = null;
		}
		return false;
	}
	
	public Dialog getCurrentDialog() {
		return currentDialog;
	}
}
