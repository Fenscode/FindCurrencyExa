package com.example.mind.findcurrencyexa;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by android on 4/1/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void addFragment(Fragment fragment) {
        pushFragment(fragment, true,null);
    }

    public void addFragment(Fragment fragment, Integer containerId) {
        pushFragment(fragment, true, containerId);
    }

    public void replaceFragment(Fragment fragment) {
        pushFragment(fragment, false, null);
    }

    public void replaceFragment(Fragment fragment, Integer containerId) {
        pushFragment(fragment, false, containerId);
    }

    public void pushFragment(Fragment fragment, boolean addToBackStack, Integer containerId) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (containerId==null) {
            containerId = R.id.fl_container;
        }

        Fragment currentFragment = fragmentManager.findFragmentById(containerId);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (currentFragment!=null) {
            fragmentTransaction.hide(currentFragment);
        }

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.add(containerId, fragment);
        } else {
            fragmentTransaction.replace(containerId, fragment);
        }

        hideKeyboard();

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
