package ttest.example.com;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by hp_pc on 23.08.2016.
 */
public class GameRowLayout extends LinearLayout {

    public GameRowLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public GameRowLayout(final Context context, final int level, final OnClickListener clickListener) {
        super(context);
        setOrientation(HORIZONTAL);

        for (int i = 0; i < level; ++i) {
            final Button button = new Button(context);
            button.setOnClickListener(clickListener);
            button.setTag(i + 1);
            button.setSingleLine(true);
            button.setText(String.valueOf(i + 1));
            button.setPadding(0, 0, 0, 0);

            addView(button, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
        }
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (!enabled) {
            final int count = getChildCount();
            for (int i = 0; i < count; i++) {
                getChildAt(i).setEnabled(false);
            }
            setBackgroundColor(0xffd7d7d7);
        } else {
            super.setEnabled(true);
        }
    }
}