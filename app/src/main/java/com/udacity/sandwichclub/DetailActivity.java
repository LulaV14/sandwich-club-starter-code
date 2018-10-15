package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView mSandwichImage;
    private TextView mAlsoKnownAs, mOrigin, mDescription, mIngredients;
    private TextView mAKALabel, mOriginLabel, mDescriptionLabel, mIngredientsLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // show back arrow on toolbar
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        mSandwichImage = findViewById(R.id.image_iv);
        mAlsoKnownAs = findViewById(R.id.also_known_tv);
        mOrigin = findViewById(R.id.origin_tv);
        mDescription = findViewById(R.id.description_tv);
        mIngredients = findViewById(R.id.ingredients_tv);

        mAKALabel = findViewById(R.id.aka_label_tv);
        mOriginLabel = findViewById(R.id.origin_label_tv);
        mDescriptionLabel = findViewById(R.id.description_label_tv);
        mIngredientsLabel = findViewById(R.id.ingredients_label_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        Picasso.get()
                .load(sandwich.getImage())
                .placeholder(R.drawable.large_sandwich_placeholder)
                .error(R.drawable.large_sandwich_placeholder)
                .into(mSandwichImage);

        setTitle(sandwich.getMainName());

        if (sandwich.getDescription().equals("")) {
            mDescription.setVisibility(View.GONE);
            mDescriptionLabel.setVisibility(View.GONE);
        } else {
            mDescription.setText(sandwich.getDescription());
            mDescription.setVisibility(View.VISIBLE);
            mDescriptionLabel.setVisibility(View.VISIBLE);
        }

        if (sandwich.getPlaceOfOrigin().equals("")) {
            mOrigin.setVisibility(View.GONE);
            mOriginLabel.setVisibility(View.GONE);
        } else {
            mOrigin.setText(sandwich.getPlaceOfOrigin());
            mOrigin.setVisibility(View.VISIBLE);
            mOriginLabel.setVisibility(View.VISIBLE);
        }

        if (sandwich.getAlsoKnownAs().size() == 0) {
            mAlsoKnownAs.setVisibility(View.GONE);
            mAKALabel.setVisibility(View.GONE);
        } else {
            for (String aka : sandwich.getAlsoKnownAs()) {
                mAlsoKnownAs.append(Html.fromHtml("&#8226; " + aka + "<br/>"));
            }
            mAlsoKnownAs.setVisibility(View.VISIBLE);
            mAKALabel.setVisibility(View.VISIBLE);
        }

        if (sandwich.getIngredients().size() == 0) {
            mIngredients.setVisibility(View.GONE);
            mIngredientsLabel.setVisibility(View.GONE);
        } else {
            for (String ingredient : sandwich.getIngredients()) {
                mIngredients.append(Html.fromHtml("&#8226; " + ingredient + "<br/>"));
            }
            mIngredients.setVisibility(View.VISIBLE);
            mIngredientsLabel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
