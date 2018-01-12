package com.example.smile.ckcc_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smile.ckcc_app.activities.AboutUsActivity;
import com.example.smile.ckcc_app.activities.ContactUsActivity;
import com.example.smile.ckcc_app.activities.EventsActivity;
import com.example.smile.ckcc_app.R;
import com.example.smile.ckcc_app.activities.ScholarshipActivity;
import com.example.smile.ckcc_app.activities.SettingActivity;

/**
 * Created by Smile on 10/18/2017.
 */

public class FragementMain extends android.support.v4.app.Fragment implements View.OnClickListener{

    CardView contactCardView, eventsCardView, calendarCardView, aboutUsCardView, settingCardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragement_main, container, false);

        contactCardView = (CardView) v.findViewById(R.id.contact_card);
        contactCardView.setOnClickListener(this);

        eventsCardView = (CardView) v.findViewById(R.id.events_card);
        eventsCardView.setOnClickListener(this);

        calendarCardView = (CardView) v.findViewById(R.id.calendar_card);
        calendarCardView.setOnClickListener(this);

        aboutUsCardView = (CardView) v.findViewById(R.id.about_us_card);
        aboutUsCardView.setOnClickListener(this);

        settingCardView = (CardView) v.findViewById(R.id.setting_card);
        settingCardView.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("RUPP Mobile Application");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_card:
                startActivity(new Intent(getActivity(), ContactUsActivity.class));
                break;

            case R.id.events_card:
                startActivity(new Intent(getActivity(), EventsActivity.class));
                break;

            case R.id.calendar_card:
                startActivity(new Intent(getActivity(), ScholarshipActivity.class));
                break;

            case R.id.about_us_card:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;

            case R.id.setting_card:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }

    }
}
