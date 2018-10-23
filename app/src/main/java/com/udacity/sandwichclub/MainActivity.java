package com.udacity.sandwichclub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private SandwichAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Sandwich> sandwichList;
    @BindView(R.id.sandwich_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sandwichList = getSandwichData();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter = new SandwichAdapter(sandwichList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Sandwich> getSandwichData () {
        List<Sandwich> sandwichList = new ArrayList<>();
        String[] sandwichDetails = getResources().getStringArray(R.array.sandwich_details);
        for(String sandwichJSON : sandwichDetails) {
            sandwichList.add(JsonUtils.parseSandwichJson(sandwichJSON));
        }
        return sandwichList;
    }
}
