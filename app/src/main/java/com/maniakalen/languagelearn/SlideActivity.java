package com.maniakalen.languagelearn;

import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class SlideActivity extends AppCompatActivity implements SlideFragment.OnFragmentInteractionListener {

    public static final String RES_EXTRA_NAME = "resource_id";
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        int resource = getIntent().getIntExtra(SlideActivity.RES_EXTRA_NAME,0);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), resource);
        mPager.setAdapter(mPagerAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    /*@Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private int resId;
        public ScreenSlidePagerAdapter(FragmentManager fm, int resourceId) {
            super(fm);
            resId = resourceId;
        }

        @Override
        public Fragment getItem(int position) {
            if (resId == 0) {
                throw new Resources.NotFoundException("Resource not found");
            }
            Resources res = getResources();
            String[] items = res.getStringArray(resId);
            return SlideFragment.newInstance(items[position]);
        }

        @Override
        public int getCount() {
            if (resId == 0) {
                throw new Resources.NotFoundException("Resource not found");
            }
            Resources res = getResources();
            String[] items = res.getStringArray(resId);
            return items.length;
        }
    }

}
