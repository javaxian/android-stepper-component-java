package com.javaxian.cleanarchitecture.steppercomponent.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.javaxian.cleanarchitecture.steppercomponent.R;
import com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.StepFragment;
import com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.StepperManagerFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {

        /*
        * You can send predefined/preloaded fragments to send it to stepper manager (StepperManagerFragment.java)
        *
        * If you don't get fragments in this part, use interactor class (StepperInteractorImpl.java)
        * to implement get fragments logic
        *
        * */

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(StepFragment.newInstance("This is step one preload"));
        fragments.add(StepFragment.newInstance("This is step two preload"));
        fragments.add(StepFragment.newInstance("This is step three preload"));
        fragments.add(StepFragment.newInstance("This is step four preload"));

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, StepperManagerFragment.newInstance(StepperManagerFragment.TYPE_STEP_TEXT, null))
                .commit();
    }
}
