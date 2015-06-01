package com.wryday.upanddownscoring.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wryday.upanddownscoring.Constant;
import com.wryday.upanddownscoring.Player;
import com.wryday.upanddownscoring.R;
import com.wryday.upanddownscoring.SharedPreferencesManager;
import com.wryday.upanddownscoring.adapter.RoundAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScoringActivity extends Activity {
    private static final String TAG = ScoringActivity.class.getSimpleName();

    private ListView mListView;

    private RoundAdapter mAdapter;

    private List<Player> mPlayers;
    private int mNumberOfRounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_scoring);

        Bundle extras = getIntent().getExtras();

        String[] playerNames = extras.getStringArray(Constant.PLAYER_NAMES);

        if (playerNames == null) {
            Toast.makeText(this, "ERROR, No player names found!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error, no player names found.");
            finish();
            return;
        }

        mPlayers = new ArrayList<>();
        for (String name : playerNames) {
            mPlayers.add(new Player(name, 0, 0));
        }

        String gameType = SharedPreferencesManager.getInstance(getApplicationContext()).getGameType();

        switch (gameType) {
            case Constant.GAME_TYPE_5_CARD:
                mNumberOfRounds = 5;
                break;
            case Constant.GAME_TYPE_7_CARD:
                mNumberOfRounds = 7;
                break;
            default:
                Log.e(TAG, "Error, game type not specified or unrecognized.");
                finish();
                return;
        }

        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new RoundAdapter(mPlayers, mNumberOfRounds + mNumberOfRounds - 1);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected item: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
