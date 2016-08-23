package ttest.example.com;

import android.content.Context;
import android.view.View;

/**
 * Created by hp_pc on 23.08.2016.
 */
public interface MyView {

    void addRow(View row);

    void removeAllViews();

    void updateLevelAndScore(int l, int s);

    Context getContext();
}
