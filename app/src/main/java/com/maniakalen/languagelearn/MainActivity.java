package com.maniakalen.languagelearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public void runAlphabet(View view) {
        runSlideActivity(R.array.alphabet);
    }
    public void runNumbers(View view) { runSlideActivity(R.array.numbers); }
    private void runSlideActivity(int resourceId) {
        Intent intent = new Intent(this, SlideActivity.class);
        intent.putExtra(SlideActivity.RES_EXTRA_NAME, resourceId);
        startActivity(intent);
    }
}
