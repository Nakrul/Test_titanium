package ttest.example.com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by hp_pc on 23.08.2016.
 */
public class MainActivity extends AppCompatActivity implements MyView {

    private Game game;

    private LinearLayout mPlayingField;
    private Button mStartGameButton;
    private ScrollView mScrollView;

    private TextView curLevel;
    private TextView totalScore;


    @SuppressLint("PrivateResource")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initViews();

        game = new Game(this);

        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.startGame();
                mStartGameButton.setEnabled(false);
            }
        });

        super.onCreate(savedInstanceState);
    }

    private void initViews() {
        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);


        FrameLayout menu = new FrameLayout(this);

        curLevel = new TextView(this);
        curLevel.setText(getString(R.string.cur_level, 0));
        curLevel.setTextColor(0xff212121);
        curLevel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        curLevel.setSingleLine(true);
        menu.addView(curLevel, LayoutHelper.createFrame(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.START | Gravity.TOP, 17, 10, 17, 0));

        totalScore = new TextView(this);
        totalScore.setText(getString(R.string.cur_score, 0));
        totalScore.setTextColor(0xff8a8a8a);
        totalScore.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        totalScore.setSingleLine(true);
        menu.addView(totalScore, LayoutHelper.createFrame(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.START | Gravity.TOP, 17, 30, 17, 0));

        mStartGameButton = new Button(this);
        mStartGameButton.setText(R.string.start_game);
        menu.addView(mStartGameButton, LayoutHelper.createFrame(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.END | Gravity.CENTER_VERTICAL, 14, 0, 14, 0));

        baseLayout.addView(menu, LayoutParams.MATCH_PARENT, LayoutHelper.todp(64));


        mScrollView = new ScrollView(this);
        mPlayingField = new LinearLayout(this);
        mPlayingField.setOrientation(LinearLayout.VERTICAL);
        mScrollView.addView(mPlayingField, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        baseLayout.addView(mScrollView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        setContentView(baseLayout);
    }

    @Override
    public void addRow(View row) {

        int count = mPlayingField.getChildCount();
        if (0 < count) {
            for (int i = 0; i < count; i++) {
                mPlayingField.getChildAt(i).setEnabled(false);
            }
        }

        mPlayingField.addView(row, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));

        if (Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation) {
            mScrollView.post(new Runnable() {
                @Override
                public void run() {
                    mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    @Override
    public void removeAllViews() {
        mPlayingField.removeAllViews();
        mStartGameButton.setEnabled(true);
        updateLevelAndScore(0, 0);
    }

    @Override
    public void updateLevelAndScore(int l, int s) {
        curLevel.setText(getString(R.string.cur_level, l));
        totalScore.setText(getString(R.string.cur_score, s));
    }

    @Override
    public Context getContext() {
        return this;
    }
}
