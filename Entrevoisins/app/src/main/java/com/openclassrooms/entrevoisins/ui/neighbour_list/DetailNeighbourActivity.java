package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @BindView(R.id.activity_detail_neighbour_avatar_picture)
    public ImageView mAvatarPicture;

    @BindView(R.id.back_btn)
    public ImageButton mBackBtn;

    @BindView(R.id.activity_detail_neighbour_first_name)
    public TextView mFirstName;

    @BindView(R.id.detail_Card_Name)
    public TextView mCardName;

    @BindView(R.id.detail_home_card_img)
    public ImageView mHomeImg;

    @BindView(R.id.detail_home_card_text)
    public TextView mHomeText;

    @BindView(R.id.detail_tel_card_img)
    public ImageView mTelImg;

    @BindView(R.id.detail_tel_card_text)
    public TextView mTelText;

    @BindView(R.id.detail_site_card_img)
    public ImageView mSiteImg;

    @BindView(R.id.detail_site_card_text)
    public TextView mSiteText;

    @BindView(R.id.detail_about_me)
    public TextView mDetailAbout;

    @BindView(R.id.fav_btn)
    public android.support.design.widget.FloatingActionButton mFavBtn;

    @BindDrawable(R.drawable.ic_baseline_star__yellow_24)
    public Drawable mFavClick;

    @BindDrawable(R.drawable.ic_baseline_star_24)
    public Drawable mFavNoClick;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);


        mApiService = DI.getNeighbourApiService();
        getNeighbour();
        seeDetail();
        favBtn();

    }

    private void getNeighbour() {
        mNeighbour = getIntent().getParcelableExtra("neighbour");
    }

    private void seeDetail() {
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mAvatarPicture);
        mFirstName.setText(mNeighbour.getName());
        mCardName.setText(mNeighbour.getName());
        mHomeText.setText(mNeighbour.getAddress());
        mTelText.setText(mNeighbour.getPhoneNumber());
        String siteText = "www.facebook.fr/" + mNeighbour.getName();
        mSiteText.setText(siteText);
        mDetailAbout.setText(mNeighbour.getAboutMe());
        mBackBtn.setOnClickListener(v -> finish());

        if (mApiService.getNeighboursFavorite().contains(mNeighbour)) {

            mFavBtn.setImageDrawable(mFavClick);
        }
    }

    private void favBtn() {
        mFavBtn.setOnClickListener(v -> {
            mApiService.toggleNeighbour(mNeighbour);
            mNeighbour.setFav(!mNeighbour.isFav());
            if (mNeighbour.isFav()) {
                mFavBtn.setImageDrawable(mFavClick);
            } else {
                mFavBtn.setImageDrawable(mFavNoClick);
            }
        });
    }
}







