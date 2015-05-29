package com.wryday.upanddownscoring.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wryday.upanddownscoring.Constant;
import com.wryday.upanddownscoring.Player;
import com.wryday.upanddownscoring.R;
import com.wryday.upanddownscoring.Round;

import java.util.ArrayList;
import java.util.List;

public class RoundAdapter extends BaseAdapter {

    private List<Player> mPlayers;
    private int mRoundCount;

    private List<Round> mRounds;

    public RoundAdapter(List<Player> players, int rounds) {
        mPlayers = players;
        mRoundCount = rounds;

        mRounds = new ArrayList<>();


        for (int i = 0; i < rounds; i++) {
            mRounds.add(new Round(players));
        }
    }

    @Override
    public int getCount() {
        return mRoundCount;
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
            viewHolder = new ViewHolder(view, mPlayers.size());
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.bind(mRounds.get(position), position);

        return view;
    }

    private class ViewHolder {
        private LinearLayout mParent;
        private TextView mTextView;

        public ViewHolder(View root, int numPlayers) {
            mParent = (LinearLayout) root;
            mTextView = (TextView) root.findViewById(R.id.text_view);

            LinearLayout linearLayout = new LinearLayout(root.getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//            linearLayout.set

            for (int i = 0; i < numPlayers; i++) {
                TextView textView = new TextView(root.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                textView.setLayoutParams(params);
                textView.setPadding(4, 4, 4, 4);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.item_background_round);
                textView.setText(mPlayers.get(i).getName() + "\nBid: 0\nActual: 0\nScore:" + i);
                linearLayout.addView(textView);
            }

            mParent.addView(linearLayout);
        }

        public void bind(Round round, int position) {
            mTextView.setText("Round " + (position + 1) + " (Deal " + Constant.getCardsDealtByRound(mRoundCount, position) + " cards)");
        }
    }
}
