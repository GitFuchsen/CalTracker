package de.fhdw.bfwi115a.caltracker.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityFoods extends AppCompatActivity {

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        //Set the fragment initially
        FragmentFoods fragment = new FragmentFoods();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentFoods_container_itemList, fragment);
        fragmentTransaction.commit();

        // toolbar_foods is defined in layout file app_bar_foods
        Toolbar toolbar_foods = (Toolbar) findViewById(R.id.toolbar_foods);
        setSupportActionBar(toolbar_foods);
        /*
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_foods, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        mSearchAction = menu.findItem(R.id.action_searchFood);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_searchFood:
                handleMenuSearch();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void handleMenuSearch() {
        final ActionBar action = getSupportActionBar();

        if(isSearchOpened) {
            action.setDisplayShowCustomEnabled(false);
            action.setDisplayShowTitleEnabled(true);

            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(editSearch.getWindowToken(),0);

            mSearchAction.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_open_search, null));

            isSearchOpened = false;
        }else {

            action.setDisplayShowCustomEnabled(true);

            action.setCustomView(R.layout.search_bar);
            action.setDisplayShowTitleEnabled(false);

            editSearch = (EditText) action.getCustomView().findViewById(R.id.editSearch);

            editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });

            editSearch.requestFocus();

            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editSearch, InputMethodManager.SHOW_IMPLICIT);

            mSearchAction.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close, null));

            isSearchOpened = true;
        }
    }

    @Override
    public void onBackPressed(){
        if(isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    private void doSearch() {
        // Search food entry
    }
}
