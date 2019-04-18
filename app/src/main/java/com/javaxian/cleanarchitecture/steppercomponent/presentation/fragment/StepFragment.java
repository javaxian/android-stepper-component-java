package com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javaxian.cleanarchitecture.steppercomponent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment {

    View rootView;
    TextView stepTextView;
    String stepText = "Hello";

    public static StepFragment newInstance(String stepText) {

        StepFragment stepFragment = new StepFragment();
        stepFragment.stepText = stepText;

        return stepFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_step, container, false);

        stepTextView = (TextView) rootView.findViewById(R.id.stepText);

        initResources();

        return rootView;
    }

    private void initResources(){

        stepTextView.setText(stepText);
    }

}
