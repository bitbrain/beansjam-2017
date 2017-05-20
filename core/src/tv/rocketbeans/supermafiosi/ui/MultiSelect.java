package tv.rocketbeans.supermafiosi.ui;

import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MultiSelect<T> extends Table {

	public interface MultiSelectListener<T> {
		void onSelect(T key);
	}
	
	public MultiSelect(final MultiSelectListener<T> listener, Map<T, String> values) {
		for (final Entry<T, String> entry : values.entrySet()) {
			TextButton option = new TextButton(entry.getValue(), Styles.BUTTON_MULTIPLE_CHOICE_OPTION);
			option.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					listener.onSelect(entry.getKey());
				}
			});
			add(option).width(Gdx.graphics.getWidth() - 50f).height(75f).padBottom(10f).row();
		}
	}
}
