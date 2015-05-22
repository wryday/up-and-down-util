package com.wryday.upanddownscoring.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wryday.upanddownscoring.Player;
import com.wryday.upanddownscoring.R;

import java.util.List;

public class RoundAdapter extends BaseAdapter {

    private List<Player> mPlayers;

    public RoundAdapter(List<Player> players, int rounds) {
        mPlayers = players;
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
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_round_list, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.bind(mPlayers.get(position));

        return view;
    }

    private class ViewHolder {
        private TextView mTextView;

        public ViewHolder(View root) {
            mTextView = (TextView) root.findViewById(R.id.text_view);
        }

        public void bind(Player player) {
            mTextView.setText(player.getName() + " Score: " + player.getScore());
        }
    }
}
