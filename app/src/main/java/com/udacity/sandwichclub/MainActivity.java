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

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SandwichAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Sandwich> sandwichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sandwichList = getSandwichData();
        mRecyclerView = findViewById(R.id.sandwich_recycler_view);
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
