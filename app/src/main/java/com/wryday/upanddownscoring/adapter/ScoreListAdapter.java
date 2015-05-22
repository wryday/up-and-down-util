package com.wryday.upanddownscoring.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wryday.upanddownscoring.R;

import java.util.ArrayList;
import java.util.List;

public class ScoreListAdapter extends BaseAdapter {

    private List<String> mItems = new ArrayList<>();

    public ScoreListAdapter() {

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_list, parent, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.bind(mItems.get(position), mItems.get(position), mItems.get(position));


        return null;
    }

    private class ViewHolder {

        private TextView mBidTextView;
        private TextView mTricksTextView;
        private TextView mScoreTextView;

        public ViewHolder(View root) {
            mBidTextView = (TextView) root.findViewById(R.id.bid_text_view);
            mTricksTextView = (TextView) root.findViewById(R.id.tricks_text_view);
            mScoreTextView = (TextView) root.findViewById(R.id.score_text_view);
        }

        public void bind(String bid, String tricks, String score) {
            mBidTextView.setText(bid);
            mTricksTextView.setText(tricks);
            mScoreTextView.setText(score);
        }
    }
}
