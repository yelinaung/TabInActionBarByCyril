package com.yelinaung.cyrilmottiertabinab.app;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
  private ViewPager mPager;
  private TabView tabViews;
  private TabBarView tabBarView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    LayoutInflater inflator =
        (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View v = inflator.inflate(R.layout.tabbars, null);
    tabBarView = (TabBarView) v.findViewById(R.id.tab_bar);

    v.setLayoutParams(new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT));

    getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    getActionBar().setCustomView(v);

    mPager = (ViewPager) findViewById(R.id.pagerContent);

    mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override public void onPageScrolled(int position, float positionOffset,
          int positionOffsetPixels) {
        tabBarView.setStripColor(getResources().getColor(android.R.color.white));
      }

      @Override public void onPageSelected(int position) {
        tabBarView.setStripColor(getResources().getColor(android.R.color.white));
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });

    tabBarView.setStripColor(getResources().getColor(android.R.color.white));

    mPager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      assert rootView != null;
      TextView textView = (TextView) rootView.findViewById(R.id.section_label);
      textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
      return rootView;
    }
  }

  public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public int getCount() {
      return 3;
    }

    @Override
    public Fragment getItem(int position) {
      return PlaceholderFragment.newInstance(position);
    }
  }
}
