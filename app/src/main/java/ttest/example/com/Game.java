package ttest.example.com;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by hp_pc on 23.08.2016.
 */
class Game {

    private static final int LEVELS = 11;

    private final MyView mActivity;

    private int mCurrentLevel;
    private int mCurrentScore;
    private int mCurrentMine;

    public Game(final MyView v) {
        mActivity = v;
    }

    public void startGame() {
        updateLevelAndScore();
        mActivity.addRow(new GameRowLayout(mActivity.getContext(), LEVELS - mCurrentLevel, playerClick()));
    }

    private void updateLevelAndScore() {
        mCurrentScore += mCurrentLevel;
        mActivity.updateLevelAndScore(++mCurrentLevel, mCurrentScore);

    }

    private OnClickListener playerClick() {
        return new OnClickListener() {
            @Override
            public void onClick(final View v) {
                Context context = mActivity.getContext();
                if (mCurrentMine == (int)v.getTag()) {
                    createDialog(context.getString(R.string.lose_mess));
                    return;
                } else if (LEVELS - mCurrentLevel == 2) {
                    updateLevelAndScore();
                    createDialog(context.getString(R.string.won_mess));
                    return;
                }

                updateLevelAndScore();
                mActivity.addRow(new GameRowLayout(context, LEVELS - mCurrentLevel, playerClick()));
                mCurrentMine = (int) (Math.random() * (LEVELS - mCurrentLevel)) + 1;
                Log.d("@@@@@@@@@@@@",mCurrentMine+"");
            }
        };
    }

    private void resetData() {
        mActivity.removeAllViews();
        mCurrentLevel = 0;
        mCurrentScore = 0;
        mCurrentMine = 0;
    }

    private void createDialog(final String s) {
        final Context context = mActivity.getContext();
        new Builder(context)
                .setTitle(s)
                .setMessage(context.getString(R.string.final_score, mCurrentScore))
                .setCancelable(false)
                .setNeutralButton(context.getText(R.string.exit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        ((Activity) mActivity).finish();
                    }
                })
                .setPositiveButton(context.getText(R.string.again), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetData();
                    }
                }).create().show();
    }
}