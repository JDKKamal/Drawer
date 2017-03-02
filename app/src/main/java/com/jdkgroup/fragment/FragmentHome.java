package com.jdkgroup.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jdkgroup.drawer.R;
import com.jdkgroup.general.General;
import com.jdkgroup.general.GeneralImplement;
import com.jdkgroup.model.GSONCategory;
import java.util.HashMap;
import java.util.Map;

public class FragmentHome extends Fragment implements View.OnClickListener {
    private View view;

    private Activity activity;

    private General general;
    private Gson gson;
    private GSONCategory gSONCategory;
    private Map utilMap;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);

        activity = getActivity();

        general = new GeneralImplement();
        utilMap = new HashMap();


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
