package tv.rocketbeans.supermafiosi.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.InputAdapter;

import tv.rocketbeans.supermafiosi.i18n.Bundle;

public class DialogManager extends InputAdapter {
	
	public static interface DialogManagerListener {
		void afterLastDialog();
		void onDialog(Dialog dialog);
	}
	
	private List<Dialog> dialogs = new ArrayList<Dialog>();
	private Set<DialogManagerListener> dialogManagerListeners = new HashSet<DialogManagerListener>();
	
	private Dialog currentDialog;

	public void addDialog(String title, String dialogKey, String avatarKey) {
		dialogs.add(new Dialog(title, Bundle.translations.get(dialogKey), avatarKey));
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return nextDialog();
	}
	
	public void addListener(DialogManagerListener listener) {
		this.dialogManagerListeners.add(listener);
	}
	
	public void removeListener(DialogManagerListener listener) {
		this.dialogManagerListeners.remove(listener);
	}
	
	public boolean hasDialogs() {
		return !dialogs.isEmpty();
	}
	
	public boolean nextDialog() {
		if (dialogs.size() > 0) {
			currentDialog = dialogs.remove(0);
			for (DialogManagerListener listener : dialogManagerListeners) {
				listener.onDialog(currentDialog);
			}
			return true;
		} else {
			for (DialogManagerListener listener : dialogManagerListeners) {
				listener.afterLastDialog();
			}
			currentDialog = null;
		}
		return false;
	}
	
	public Dialog getCurrentDialog() {
		return currentDialog;
	}
}
