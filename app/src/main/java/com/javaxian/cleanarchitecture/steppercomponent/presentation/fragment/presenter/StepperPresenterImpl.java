package com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.presenter;

import android.support.v4.app.Fragment;

import com.javaxian.cleanarchitecture.steppercomponent.domain.base.BasePresenter;
import com.javaxian.cleanarchitecture.steppercomponent.domain.interactor.IStepperInteractor;
import com.javaxian.cleanarchitecture.steppercomponent.domain.interactor.StepperInteractorImpl;

import java.util.ArrayList;

public class StepperPresenterImpl extends BasePresenter<IStepperPresenter.View> implements IStepperPresenter{

    private IStepperInteractor interactor;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int type;

    public StepperPresenterImpl(int type, ArrayList<Fragment> fragments){
        this.type = type;
        interactor = new StepperInteractorImpl();

        if(fragments!=null){
            this.fragments = fragments;
        }else{
            this.fragments = interactor.getSteps();
        }

    }

    @Override
    public void initialize() {
        super.initialize();
        getView().loadSteppers(fragments);
    }

    @Override
    public void onStepSelected(int position) {

        if(type==1){
            getView().updateStepText(position + 1);
        }else if(type==2){
            int progress = (100 / (fragments.size())) * (position + 1);
            getView().updateStepProgress(position, progress);
        }else if(type==3){
            getView().updateDotIndicatorIndex(position);
        }

        if (position == 0) {
            getView().disableBack();
        } else if (position == (fragments.size() - 1)) {
            getView().disableNext();
        } else {
            getView().enableBack();
            getView().enableNext();
        }
    }

    @Override
    public void onPreviousStep(int position) {
        getView().showStepByIndex(position-1);
    }

    @Override
    public void onNextStep(int position) {
        getView().showStepByIndex(position+1);
    }
}
