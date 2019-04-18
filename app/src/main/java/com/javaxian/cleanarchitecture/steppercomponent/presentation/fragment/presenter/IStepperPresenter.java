package com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.presenter;

import android.support.v4.app.Fragment;

import com.javaxian.cleanarchitecture.steppercomponent.domain.base.BasePresenter;

import java.util.ArrayList;

public interface IStepperPresenter {

    void onStepSelected(int position);
    void onPreviousStep(int position);
    void onNextStep(int position);


    interface View extends BasePresenter.View {

        void init();

        void loadSteppers(ArrayList<Fragment> fragments);

        void updateStepProgress(int position, int progress);

        void updateStepText(int position);

        void updateDotIndicatorIndex(int position);

        void showStepByIndex(int position);

        void disableBack();

        void disableNext();

        void enableBack();

        void enableNext();
    }
}
