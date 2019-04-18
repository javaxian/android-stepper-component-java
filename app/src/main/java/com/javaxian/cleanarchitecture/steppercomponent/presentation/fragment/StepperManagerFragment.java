package com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.javaxian.cleanarchitecture.steppercomponent.R;
import com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.presenter.IStepperPresenter;
import com.javaxian.cleanarchitecture.steppercomponent.presentation.fragment.presenter.StepperPresenterImpl;
import com.javaxian.cleanarchitecture.steppercomponent.presentation.view.adapter.PagerStepperAdapter;
import com.javaxian.cleanarchitecture.steppercomponent.presentation.view.custom.CustomViewPager;

import java.util.ArrayList;

public class StepperManagerFragment extends Fragment implements View.OnClickListener, IStepperPresenter.View {

    View rootView;

    View headerLayout;
    TextView textStepIndex;

    View progressBarLayout;

    LinearLayout dotIndicatorLayout;
    ImageView[] dots;

    Button backButton;
    Button nextButton;

    CustomViewPager customViewPager;

    ProgressBar progressBar;

    private StepperPresenterImpl presenter;
    ArrayList<Fragment> fragments;

    int numberOfSteps = 0;
    int type = 1;

    public static final int TYPE_STEP_TEXT = 1;
    public static final int TYPE_STEP_PROGRESS_BAR = 2;
    public static final int TYPE_STEP_DOT = 3;

    public static StepperManagerFragment newInstance(int type) {

        StepperManagerFragment stepperManagerFragment = new StepperManagerFragment();
        stepperManagerFragment.type = type;

        return stepperManagerFragment;
    }

    public static StepperManagerFragment newInstance(int type, ArrayList<Fragment> fragments){
        StepperManagerFragment stepperManagerFragment = new StepperManagerFragment();
        stepperManagerFragment.type = type;
        stepperManagerFragment.fragments = fragments;

        return stepperManagerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_stepper_manager, container, false);

        headerLayout = (View) rootView.findViewById(R.id.header_layout);
        textStepIndex = (TextView) rootView.findViewById(R.id.textStepIndex);

        progressBarLayout = (View) rootView.findViewById(R.id.progress_bar_layout);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        dotIndicatorLayout = (LinearLayout) rootView.findViewById(R.id.dot_indicator_layout);

        backButton = (Button) rootView.findViewById(R.id.backButton);
        nextButton = (Button) rootView.findViewById(R.id.nextButton);

        customViewPager = (CustomViewPager) rootView.findViewById(R.id.customPager);

        initPresenter();
        initResources();

        return rootView;
    }

    private void initPresenter() {
        presenter = new StepperPresenterImpl(type,fragments);
        presenter.setActivity(getActivity());
        presenter.setContext(getContext());
        presenter.setView(this);
        presenter.initialize();
    }

    private void initResources() {

        if (type == 1) {
            headerLayout.setVisibility(View.VISIBLE);
            progressBarLayout.setVisibility(View.INVISIBLE);
            dotIndicatorLayout.setVisibility(View.GONE);
        } else if (type == 2) {
            headerLayout.setVisibility(View.GONE);
            progressBarLayout.setVisibility(View.VISIBLE);
            dotIndicatorLayout.setVisibility(View.GONE);
        } else if (type == 3) {
            headerLayout.setVisibility(View.GONE);
            progressBarLayout.setVisibility(View.GONE);
            dotIndicatorLayout.setVisibility(View.VISIBLE);
        }

        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        customViewPager.setPagingEnabled(false);

        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.onStepSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backButton:
                presenter.onPreviousStep(customViewPager.getCurrentItem());
                break;

            case R.id.nextButton:
                presenter.onNextStep(customViewPager.getCurrentItem());
                break;
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void loadSteppers(ArrayList<Fragment> fragments) {

        numberOfSteps = fragments.size();

        if (type == TYPE_STEP_DOT) {

            generateDotIndicator(fragments.size());
            customViewPager.setAdapter(new PagerStepperAdapter(getChildFragmentManager(), fragments));

        } else if (type == TYPE_STEP_PROGRESS_BAR) {

            int startPosition = 1;
            int maxProgress = (100 / numberOfSteps) * numberOfSteps;
            int currentProgress = (100 / numberOfSteps) * startPosition;
            customViewPager.setAdapter(new PagerStepperAdapter(getChildFragmentManager(), fragments));
            progressBar.setMax(maxProgress);
            progressBar.setProgress(currentProgress);

        } else if (type == TYPE_STEP_TEXT){
            customViewPager.setAdapter(new PagerStepperAdapter(getChildFragmentManager(), fragments));
        }


    }

    private void generateDotIndicator(int size) {
        dots = new ImageView[size];

        for (int i = 0; i < size; i++) {

            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_selected_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            dotIndicatorLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_dot));
    }


    @Override
    public void updateStepProgress(int position, int progress) {

        progressBar.setProgress(progress);

    }

    @Override
    public void updateStepText(int position) {
        textStepIndex.setText("Step " + position + " of " + numberOfSteps);
    }

    @Override
    public void updateDotIndicatorIndex(int position) {
        for (int i=0;i<numberOfSteps;i++){
            if(position!=i){
                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_selected_dot));
            }else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_dot));
            }
        }
    }

    @Override
    public void showStepByIndex(int position) {
        customViewPager.setCurrentItem(position);
        customViewPager.invalidate();
    }

    @Override
    public void disableBack() {
        backButton.setEnabled(false);
    }

    @Override
    public void disableNext() {
        nextButton.setEnabled(false);
    }

    @Override
    public void enableBack() {
        backButton.setEnabled(true);
    }

    @Override
    public void enableNext() {
        nextButton.setEnabled(true);
    }
}
