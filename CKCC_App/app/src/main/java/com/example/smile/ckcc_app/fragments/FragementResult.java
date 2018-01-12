package com.example.smile.ckcc_app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import com.example.smile.ckcc_app.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Smile on 10/18/2017.
 */

public class FragementResult extends android.support.v4.app.Fragment {

    TextView txDb, txOs, txDm, txMath, txEnter, txAndroid, txNetwork;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragement_result, container, false);

        txDb = (TextView)v.findViewById(R.id.db);
        txAndroid = (TextView)v.findViewById(R.id.andriod);
        txOs = (TextView)v.findViewById(R.id.os);
        txMath =(TextView)v.findViewById(R.id.math);
        txNetwork = (TextView)v.findViewById(R.id.network);
        txDm = (TextView)v.findViewById(R.id.dm);
        txEnter = (TextView)v.findViewById(R.id.enterpreneur);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Result");
    }
}
