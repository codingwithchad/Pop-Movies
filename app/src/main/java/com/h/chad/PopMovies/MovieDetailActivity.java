package com.h.chad.PopMovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.h.chad.PopMovies.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.h.chad.PopMovies.MovieAdapter.MOVIE_PLOT;
import static com.h.chad.PopMovies.MovieAdapter.MOVIE_POSTER_PATH;
import static com.h.chad.PopMovies.MovieAdapter.MOVIE_RELEASE_DATE;
import static com.h.chad.PopMovies.MovieAdapter.MOVIE_TITLE;
import static com.h.chad.PopMovies.MovieAdapter.MOVIE_VOTE_AVERAGE;


/**
 * Created by chad on 5/11/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    /*butterknife binding */

    @BindView(R.id.tv_movie_title) TextView mTV_movieTitle;
    @BindView(R.id.detail_poster)ImageView mIV_moviePoster;
    @BindView (R.id.tv_release_date)TextView mTV_movieReleaseDate;
    @BindView(R.id.rb_vote_average)RatingBar mRB_movieVoteAverage;
    @BindView(R.id.tv_average) TextView mTV_movieVoteAverage;
    @BindView(R.id.tv_plot) TextView mTV_moviePlot;
    private final static String LOG_TAG = MovieDetailActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_item);
        ButterKnife.bind(this);
        //Get the Intent that started the activity
        Intent intent = getIntent();
        Context context = this;
        String movieTitle = intent.getStringExtra(MOVIE_TITLE);
        String movieReleaseDate = intent.getStringExtra(MOVIE_RELEASE_DATE);
        String moviePosterPath = intent.getStringExtra(MOVIE_POSTER_PATH);
        String moviePlot = intent.getStringExtra(MOVIE_PLOT);
        Double movieVoteAverage = intent.getDoubleExtra(MOVIE_VOTE_AVERAGE, 0.0);

        String outOfTen = this.getString(R.string.average_out_of_ten);
        String averageVotes = Double.toString(movieVoteAverage) + outOfTen;

        //todo 502 implement butterknife
        mTV_movieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mIV_moviePoster = (ImageView) findViewById(R.id.detail_poster);
        mTV_movieReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mRB_movieVoteAverage = (RatingBar) findViewById(R.id.rb_vote_average);
        mTV_moviePlot = (TextView) findViewById(R.id.tv_plot);
        mTV_movieVoteAverage = (TextView) findViewById(R.id.tv_average);

        String totalUrl = com.h.chad.PopMovies.MovieAdapter.IMAGE_URL + moviePosterPath;
        Picasso.with(context).load(totalUrl).into(mIV_moviePoster);
        mTV_movieVoteAverage.setText(averageVotes);
        mTV_movieTitle.setText(movieTitle);
        mTV_movieReleaseDate.setText(parseYear(movieReleaseDate));
        mRB_movieVoteAverage.setRating(movieVoteAverage.floatValue());
        mTV_moviePlot.setText(moviePlot);
    }

    private String parseYear(String inDate) {
        String outdate[] = inDate.split("-");
        return getString(R.string.release_year) + " " + outdate[0];
    }
}
