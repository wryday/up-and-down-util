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
import android.widget.ListView;
import android.widget.TextView;

import com.wryday.upanddownscoring.R;

import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends Activity {

    private ListView mListView;
    private Button mStartGameButton;

    private PlayerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_setup);

        mListView = (ListView) findViewById(R.id.list_view);
        mAdapter = new PlayerAdapter(this);
        mListView.setAdapter(mAdapter);

        mStartGameButton = (Button) findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ScoringActivity.class);
                startActivity(startIntent);
            }
        });
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

        private class ViewHolder {
            private TextView mNameTextView;

            public ViewHolder(View root) {
                mNameTextView = (TextView) root.findViewById(R.id.name_text_view);
            }

            public void bind(String name) {
                mNameTextView.setText(name);
            }
        }
    }

}
