package tv.rocketbeans.supermafiosi.ui;

import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MultiSelect<T> extends Table {
	
	private static final float ROW_HEIGHT = 75f;

	public interface MultiSelectListener<T> {
		void onSelect(T key);
	}
	
	public interface IconProvider<T> {
		String getIconPath(T type);
	}
	
	public MultiSelect(String title, final MultiSelectListener<T> listener, Map<T, String> values) {
		this(title, listener, values, null);
	}
	
	public MultiSelect(String title, final MultiSelectListener<T> listener, Map<T, String> values,IconProvider<T> iconProvider) {
		Label titleLabel = new Label(title, Styles.LABEL_MULTISELECT_TITLE);
		add(titleLabel).padBottom(40f).row();
		for (final Entry<T, String> entry : values.entrySet()) {
			TextButton option = null;
			if (iconProvider != null) {
				String iconPath = iconProvider.getIconPath(entry.getKey());
				option = new IconTextButton(entry.getValue(), iconPath, Styles.TEXT_BUTTON_MULTIPLE_CHOICE_OPTION);
				((IconTextButton)option).setIconPadding(15f);
			} else {
				option = new TextButton(entry.getValue(), Styles.TEXT_BUTTON_MULTIPLE_CHOICE_OPTION);
			}
			option.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					listener.onSelect(entry.getKey());
				}
			});
			add(option).width(Gdx.graphics.getWidth() - 50f).height(ROW_HEIGHT).padBottom(10f).row();
		}
	}
}
