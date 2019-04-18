package com.javaxian.cleanarchitecture.steppercomponent.domain.interactor;

import android.support.v4.app.Fragment;

import com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.StepFragment;

import java.util.ArrayList;

public class StepperInteractorImpl implements IStepperInteractor {
    @Override
    public ArrayList<Fragment> getSteps() {

        /*
         * Here you need to implement the logic to get fragments
         *
         * */

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(StepFragment.newInstance("This is step one"));
        fragments.add(StepFragment.newInstance("This is step two"));
        fragments.add(StepFragment.newInstance("This is step three"));
        fragments.add(StepFragment.newInstance("This is step four"));

        return fragments;
    }
}
