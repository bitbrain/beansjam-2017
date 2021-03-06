package tv.rocketbeans.supermafiosi.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;

public class DialogManager extends InputAdapter {
	
	public static interface DialogManagerListener {
		void afterLastDialog();
		void onDialog(Dialog dialog);
	}
	
	private List<Dialog> dialogs = new ArrayList<Dialog>();
	private List<DialogManagerListener> dialogManagerListeners = new CopyOnWriteArrayList<DialogManagerListener>();
	
	private Dialog currentDialog;
	
	private int lastSize;

	public void addDialog(String title, String dialogKey, String avatarKey) {
		addDialog(title, dialogKey, avatarKey, Color.WHITE);
	}
	
	public void addDialog(String title, String dialogKey, String avatarKey, Color color) {
		dialogs.add(new Dialog(title, dialogKey, avatarKey, color));
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
			lastSize = dialogs.size();
			currentDialog = dialogs.remove(0);
			for (DialogManagerListener listener : dialogManagerListeners) {
				listener.onDialog(currentDialog);
			}
			return true;
		} else if (lastSize > 0) {
			lastSize = 0;
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
