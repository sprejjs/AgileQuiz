package com.spreys.agilequiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.dot_indicator)
    DotIndicator dotIndicator;

    @BindView(R.id.btn_next)
    Button btnNext;

    private static final int NUM_PAGES = 5;

    private ScreenSlidePagerAdapter mPagerAdapter;

    /**
     * The pager widget which displays the questions
     */
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Disable the swipe gesture
                if  (positionOffset > 0.5) {
                    mPager.setCurrentItem(position, true);
                }
            }

            @Override
            public void onPageSelected(int position) {
                dotIndicator.setSelectedItem(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //This method has been intentionally left empty
            }
        });
    }

    @OnClick(R.id.btn_next)
    public void nextAnswer() {
        mPagerAdapter.triggerAnswer(mPager.getCurrentItem());
    }

    public void answerPicked() {
        btnNext.setEnabled(true);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private List<IQuestionFragment> questionFragments;

        public void triggerAnswer(int position) {
            IQuestionFragment currentFragment = (IQuestionFragment)getItem(position);
            currentFragment
        }

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            questionFragments = new ArrayList<>();

            //Product owner
            SingleChoiceQuestionFragment productOwnerFragment = new SingleChoiceQuestionFragment();
            productOwnerFragment.setQuestion(
                    getString(R.string.pb_question),
                    new String[] { getString(R.string.pb_choice1), getString(R.string.pb_choice2),
                            getString(R.string.pb_choice3),getString(R.string.pb_choice4)},
                getString(R.string.pb_choice1)
            );
            questionFragments.add(productOwnerFragment);

            //Scrum role
            MultiChoiceQuestionFragment scrumRolesFragment = new MultiChoiceQuestionFragment();
            scrumRolesFragment.setQuestion(
                    getString(R.string.sr_question),
                    new String[] { getString(R.string.sr_choice1), getString(R.string.sr_choice2),
                            getString(R.string.sr_choice3), getString(R.string.sr_choice4)},
                    new String[] { getString(R.string.sr_choice3), getString(R.string.sr_choice4)}
            );
            questionFragments.add(scrumRolesFragment);

            //Scrum artifact
            SingleChoiceQuestionFragment scrumArtifactsFragment = new SingleChoiceQuestionFragment();
            scrumArtifactsFragment.setQuestion(
                    getString(R.string.sa_question),
                    new String[] { getString(R.string.sa_choice1), getString(R.string.sa_choice2),
                            getString(R.string.sa_choice3),getString(R.string.sa_choice4)},
                    getString(R.string.sa_choice2)
            );
            questionFragments.add(scrumArtifactsFragment);

            //Scrum meetings
            MultiChoiceQuestionFragment scrumMeetingsFragment = new MultiChoiceQuestionFragment();
            scrumMeetingsFragment.setQuestion(
                    getString(R.string.sm_question),
                    new String[] { getString(R.string.sm_choice1), getString(R.string.sm_choice2),
                            getString(R.string.sm_choice3), getString(R.string.sm_choice4)},
                    new String[] { getString(R.string.sm_choice1), getString(R.string.sm_choice3)}
            );
            questionFragments.add(scrumMeetingsFragment);

            //Scrum master
            FreeFormQuestionFragment smRoleFragment = new FreeFormQuestionFragment();
            smRoleFragment.setQuestion(getString(R.string.sm_role_question), getString(R.string.sm_role_answer));
            questionFragments.add(smRoleFragment);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment)questionFragments.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
