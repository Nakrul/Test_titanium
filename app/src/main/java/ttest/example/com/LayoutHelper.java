package ttest.example.com;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.FrameLayout.LayoutParams;

/**
 * Created by hp_pc on 23.08.2016.
 */
public class LayoutHelper {

    private LayoutHelper() {
    }

    public static LayoutParams createFrame(int w, float h, int g, float l, float t, float r, float b) {
        final LayoutParams layoutParams = new LayoutParams(getSize(w), getSize(h), g);
        layoutParams.setMargins(todp(l), todp(t), todp(r), todp(b));
        return layoutParams;
    }

    protected static int todp(float dp) {
        return (int) Math.ceil(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics()));
    }

    private static int getSize(float size) {
        return (int) (0 > size ? size : todp(size));
    }
}