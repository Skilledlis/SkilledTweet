package com.example.skilledstweet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skilledstweet.R;
import com.example.skilledstweet.pojo.Tweet;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder> {

    private static final String TWITTER_RESPONSE_FORMAT="EEE MMM dd HH:mm:ss ZZZZZZ yyyy";
    public static final String MONTH_DAY_FORMAT = "MMM d";

    private List<Tweet> tweetList = new ArrayList<>();

    public void setItems(Collection<Tweet> tweets){
        tweetList.addAll(tweets);
        notifyDataSetChanged();
    }

    public void clearItems(Collection<Tweet> tweets){
        tweetList.clear();
        notifyDataSetChanged();
    }


    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item_view, parent, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        holder.bind(tweetList.get(position));
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    class TweetViewHolder extends RecyclerView.ViewHolder{

        private ImageView userImageView;
        private TextView nameTextView;
        private TextView nickTextView;
        private TextView creationDateTextView;
        private TextView contentTextView;
        private ImageView tweetImageView;
        private TextView retweetTextView;
        private TextView  likesTextView;

        public TweetViewHolder(View itemView){
            super(itemView);
            userImageView = itemView.findViewById(R.id.profile_image_view);
            nameTextView = itemView.findViewById(R.id.author_name_text_view);
            nickTextView = itemView.findViewById(R.id.author_nick_text_view);
            creationDateTextView = itemView.findViewById(R.id.creation_date_text_view);
            contentTextView = itemView.findViewById(R.id.tweet_content_text_view);
            tweetImageView = itemView.findViewById(R.id.tweet_image_view);
            retweetTextView = itemView.findViewById(R.id.retweets_text_view);
            likesTextView = itemView.findViewById(R.id.likes_text_view);
        }

        public void bind(Tweet tweet){
            nameTextView.setText(tweet.getUser().getName());
            nickTextView.setText(tweet.getUser().getNick());
            contentTextView.setText(tweet.getText());
            retweetTextView.setText(String.valueOf(tweet.getRetweetCount()));
            likesTextView.setText(String.valueOf(tweet.getFavoriteCount()));

            String creationDateFormatted = getFormattedDate(tweet.getCreationDate());
            creationDateTextView.setText(creationDateFormatted);

            Picasso.get().load(tweet.getUser().getImageUrl()).into(userImageView);

            String tweetPhotoUrl = tweet.getImageUrl();
            Picasso.get().load(tweetPhotoUrl).into(tweetImageView);

            tweetImageView.setVisibility(tweetPhotoUrl != null ? View.VISIBLE : View.GONE);
        }

        private String getFormattedDate(String rawDate){
            SimpleDateFormat utcFormat = new SimpleDateFormat(TWITTER_RESPONSE_FORMAT, Locale.ROOT);
            SimpleDateFormat displayFormat = new SimpleDateFormat(MONTH_DAY_FORMAT, Locale.getDefault());
            try{
                Date date = utcFormat.parse(rawDate);
                return displayFormat.format(date);
            }catch (ParseException e){
                throw new RuntimeException(e);
            }
        }


    }
}
