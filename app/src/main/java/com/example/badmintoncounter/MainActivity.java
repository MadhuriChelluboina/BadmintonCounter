package com.example.badmintoncounter;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int ADVANTAGE = 0;
    int teamA_score = 0;
    int teamB_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     this method takes care of event generated on clicking +1 point button in team A by
     incrementing teamA score value by 1
     */
    public void addPointForTeamA(View view) {
        teamA_score = teamA_score + 1;
        if (ADVANTAGE == 1) {
            advantageMode(teamA_score, teamB_score);
        } else
            checkScore(teamA_score, teamB_score);
    }

    private void advantageMode(int A_score, int B_score) {
        displayTeamA_score(A_score);
        displayTeamB_score(B_score);

//        TextView snackView = findViewById(R.id.snackBar_view);
        if ((A_score - 2) == B_score) {
            disableScoreView();
            setWinnerImage("teamA");
//            Snackbar.make(snackView,
//                    " TEAM A is WINNER!! "
//                    , Snackbar.LENGTH_LONG).show();


        } else if ((B_score - 2) == A_score) {
            disableScoreView();
            setWinnerImage("teamB");
//            Snackbar.make(snackView,
//                    " TEAM B is WINNER!! "
//                    , Snackbar.LENGTH_LONG).show();

        } else if (A_score == 30) {
            disableScoreView();
            setWinnerImage("teamA");
//            Snackbar.make(snackView,
//                    " TEAM A is WINNER!! "
//                    , Snackbar.LENGTH_LONG).show();

        } else if (B_score == 30) {
            disableScoreView();
            setWinnerImage("teamB");
//            Snackbar.make(snackView,
//                    " TEAM B is WINNER!! "
//                    , Snackbar.LENGTH_LONG).show();

        }

    }

    /*
    below method displays the updated team A score on teamA_score_textview
     */
    private void displayTeamA_score(int score) {
        TextView teamA_scoreView = findViewById(R.id.teamA_score_textView);
        teamA_scoreView.setText(String.valueOf(score));
    }

    /*
   this method takes care of event generated on clicking +1 point button in team B by
   incrementing teamB score value by 1
   */
    public void addPointForTeamB(View view) {
        teamB_score = teamB_score + 1;
        if (ADVANTAGE == 1) {
            advantageMode(teamA_score, teamB_score);
        } else
            checkScore(teamA_score, teamB_score);
    }

    /*
       below method displays the updated team B score on teamB_score_textview
        */
    private void displayTeamB_score(int score) {
        TextView teamB_scoreView = findViewById(R.id.teamB_score_textView);
        teamB_scoreView.setText(String.valueOf(score));
    }

    private void disableScoreView() {

        findViewById(R.id.teamA_point_button).setEnabled(false);
        findViewById(R.id.teamB_point_button).setEnabled(false);

    }

    private void enableScoreView() {

        findViewById(R.id.teamA_point_button).setEnabled(true);
        findViewById(R.id.teamB_point_button).setEnabled(true);

    }

    private void setWinnerImage(String team) {
        findViewById(R.id.reset_button).setVisibility(View.INVISIBLE);
        final ImageButton imageButton = findViewById(R.id.winner_imageButtonView);
        imageButton.setVisibility(View.VISIBLE);
        final TextView teamTitle = findViewById(R.id.teamImg_textView);
        if (team.equalsIgnoreCase("teamA")) {
            teamTitle.setText("TEAM A");
            teamTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            teamTitle.setVisibility(View.VISIBLE);
        } else {
            teamTitle.setText("TEAM B");
            teamTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            teamTitle.setVisibility(View.VISIBLE);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetAllScores(v);
            }
        });
    }

    private void unSetWinnerImage() {

        findViewById(R.id.winner_imageButtonView).setVisibility(View.INVISIBLE);
        findViewById(R.id.teamImg_textView).setVisibility(View.INVISIBLE);
        findViewById(R.id.reset_button).setVisibility(View.VISIBLE);

    }

    private void checkScore(int A_score, int B_score) {
        displayTeamA_score(A_score);
        displayTeamB_score(B_score);
        TextView snackView = findViewById(R.id.snackBar_view);
        if (A_score == 21) {
            disableScoreView();
            setWinnerImage("teamA");
//            Snackbar.make(snackView,
//                    " TEAM A is WINNER!! "
//                    , Snackbar.LENGTH_LONG).show();

        } else if (B_score == 21) {
            disableScoreView();
            setWinnerImage("teamB");
//            Snackbar.make(snackView,
//                    " TEAM B is WINNER!! "
//                    , Snackbar.LENGTH_LONG).show();


        }
        if ((A_score == B_score) && (A_score == 20)) {

            Snackbar.make(snackView,
                    "score reached 20 for both Teams!! 2pt advantage mode is On "
                    , Snackbar.LENGTH_LONG).show();
            ADVANTAGE = 1;

        }
    }


    /*
    below method initializes 0 to both team score values and display them on screen
     */
    public void resetAllScores(View view) {
        teamA_score = 0;
        teamB_score = 0;
        ADVANTAGE = 0;
        enableScoreView();
        unSetWinnerImage();
        displayTeamA_score(teamA_score);
        displayTeamB_score(teamB_score);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ADVANTAGE", ADVANTAGE);
        outState.putInt("TeamA_score", teamA_score);
        outState.putInt("TeamB_score", teamB_score);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ADVANTAGE = savedInstanceState.getInt("ADVANTAGE");
        teamA_score = savedInstanceState.getInt("TeamA_score");
        teamB_score = savedInstanceState.getInt("TeamB_score");
        displayTeamB_score(teamB_score);
        displayTeamA_score(teamA_score);
    }
}
