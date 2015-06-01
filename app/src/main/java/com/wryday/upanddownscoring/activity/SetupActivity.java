package com.wryday.upanddownscoring.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wryday.upanddownscoring.Constant;
import com.wryday.upanddownscoring.R;
import com.wryday.upanddownscoring.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends Activity {

    private ListView mListView;
    private Button mStartGameButton;

    private RadioGroup mRadioGroup;
    private RadioButton mRadioFive;
    private RadioButton mRadioSeven;

    private PlayerAdapter mAdapter;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mRadioFive.isChecked()) {
                SharedPreferencesManager.getInstance(getApplicationContext()).setGameType(Constant.GAME_TYPE_5_CARD);
            } else if (mRadioSeven.isChecked()) {
                SharedPreferencesManager.getInstance(getApplicationContext()).setGameType(Constant.GAME_TYPE_7_CARD);
            }

            Intent startIntent = new Intent(getApplicationContext(), ScoringActivity.class);

            startIntent.putExtra(Constant.PLAYER_NAMES, mAdapter.getNames());

            startActivity(startIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_setup);

        String gameType = SharedPreferencesManager.getInstance(getApplicationContext()).getGameType();

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioFive = (RadioButton) findViewById(R.id.radio_5);
        mRadioSeven = (RadioButton) findViewById(R.id.radio_7);

        if (gameType.equals(Constant.GAME_TYPE_5_CARD)) {
            mRadioFive.setChecked(true);
        } else if (gameType.equals(Constant.GAME_TYPE_7_CARD)) {
            mRadioSeven.setChecked(true);
        }

        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new PlayerAdapter(this);
        mListView.setAdapter(mAdapter);

        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(mOnClickListener);
    }

    private class PlayerAdapter extends BaseAdapter {

        private Context mContext;

        private List<String> mPlayers = new ArrayList<>();

        public PlayerAdapter(Context context) {
            mPlayers.add("Ed");
            mPlayers.add("Joe");

            mContext = context;
        }

        @Override
        public int getCount() {
            return mPlayers.size();
        }

        @Override
        public Object getItem(int position) {
            return mPlayers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.w("TAG", "TAG A");
            View view = convertView;

            ViewHolder viewHolder;

            if (view == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                view = layoutInflater.inflate(R.layout.item_player_list, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.bind(getName(position));

            return view;
        }

        private String getName(int position) {
            return mPlayers.get(position);
        }

        public String[] getNames() {
            return mPlayers.toArray(new String[mPlayers.size()]);
        }

        private class ViewHolder {
            private EditText mNameEditText;

            public ViewHolder(View root) {
                mNameEditText = (EditText) root.findViewById(R.id.name_edit_text);
            }

            public void bind(String name) {
                mNameEditText.setText(name);
            }
        }
    }

}
