package nl.garvelink.oss.slidingfragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends Activity implements UnderFragment.Callback, FragmentManager.OnBackStackChangedListener, HorizontalSlidingFragment.Callback {

    private String NAVIGATION_FRAGMENT_TAG = "navigationFragment";
    private UnderFragment navigationFragment;
    private boolean detailFragmentVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        getFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null) {
            navigationFragment = new UnderFragment();
            getFragmentManager().beginTransaction().add(R.id.main__content_container, navigationFragment, NAVIGATION_FRAGMENT_TAG).commit();
        } else {
            navigationFragment = (UnderFragment) getFragmentManager().findFragmentByTag(NAVIGATION_FRAGMENT_TAG);
        }
    }

    @Override
    public void onButtonPressed() {
        pushFragment(new OverFragment(), "Agenda");
    }

    private void pushFragment(HorizontalSlidingFragment fragment, String label) {
        if (detailFragmentVisible)
            return;
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        fragment.addWithAnimation(tx, R.id.main__content_container, label);
        tx.commit();
    }

    private void popFragment() {
        getFragmentManager().popBackStack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            popFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        detailFragmentVisible = getFragmentManager().getBackStackEntryCount() > 0;
    }

    @Override
    public void onSlidingFragmentBeginAnimation(HorizontalSlidingFragment source, boolean willOpen) {
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        if (!willOpen) {
            // Before the sliding fragment begins moving out of screen, make the underlying navigation fragment visible.
            navigationFragment.setVisible(true);
        }
    }

    @Override
    public void onSlidingFragmentEndAnimation(HorizontalSlidingFragment source, boolean hasOpened) {
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(hasOpened);
        actionBar.setDisplayHomeAsUpEnabled(hasOpened);
        if (hasOpened) {
            // After the sliding fragment has appeared on top of the navigation fragment, hide the latter to reduce GPU overdraw.
            navigationFragment.setVisible(false);
        }
    }
}
