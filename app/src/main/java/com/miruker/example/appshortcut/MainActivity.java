package com.miruker.example.appshortcut;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.create_shortcut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
                List<ShortcutInfo> shortcutInfoList = new ArrayList<>();
                for (int i = 1; i <= 2; i++) {
                    Intent intent = DynamicShortcutActivity.createIntent(MainActivity.this, i + " number shortcuts!");
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    shortcutInfoList.add(new ShortcutInfo.Builder(getApplicationContext(), "DYNAMIC_" + i)
                            .setShortLabel(i + "dynamic shortcuts")
                            .setLongLabel(i + "dynamic shortcuts")
                            .setDisabledMessage("icon disabled!!")
                            .setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.dynamic))
                            .setIntent(intent)
                            .build());
                }

                shortcutManager.addDynamicShortcuts(shortcutInfoList);
                Toast.makeText(getApplicationContext(), "Add Dynamic Shortcut", Toast.LENGTH_SHORT).show();
            }
        });


        ((CompoundButton) findViewById(R.id.on_off)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean newValue) {
                ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
                List<String> ids = new ArrayList<>();

                if (newValue) {
                    for (ShortcutInfo info : shortcutManager.getPinnedShortcuts()) {
                        if(!info.isEnabled() && info.getId().startsWith("DYNAMIC_"))
                            ids.add(info.getId());
                    }
                    shortcutManager.enableShortcuts(ids);
                }
                else {
                    for (ShortcutInfo info : shortcutManager.getPinnedShortcuts()) {
                        if(info.isDynamic())
                            ids.add(info.getId());
                    }
                    shortcutManager.disableShortcuts(ids);
                }
            }
        });

    }
}
