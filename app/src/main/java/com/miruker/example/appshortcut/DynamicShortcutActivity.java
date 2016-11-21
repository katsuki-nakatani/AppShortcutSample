package com.miruker.example.appshortcut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by katsuki-nakatani on 2016/11/21.
 */

public class DynamicShortcutActivity extends Activity {

    private static final String PARAM_TEXT = "param_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dynamic);

        ((TextView)findViewById(R.id.text)).setText(getIntent().getStringExtra(PARAM_TEXT));
    }


    public static Intent createIntent(Context context,String text){
        Intent intent = new Intent(context,DynamicShortcutActivity.class);
        intent.putExtra(PARAM_TEXT,text);
        return intent;
    }
}
