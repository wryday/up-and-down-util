package com.wryday.upanddownscoring.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wryday.upanddownscoring.Player;
import com.wryday.upanddownscoring.R;
import com.wryday.upanddownscoring.adapter.RoundAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScoringActivity extends Activity {

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

        mPlayers = new ArrayList<>();
        mPlayers.add(new Player("Joe", 0, 0));
        mPlayers.add(new Player("Rena", 0, 0));
        mPlayers.add(new Player("Ed", 0, 0));
        mPlayers.add(new Player("Erica", 0, 0));

        mNumberOfRounds = 7;

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
