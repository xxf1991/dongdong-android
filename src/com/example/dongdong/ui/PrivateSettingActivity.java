package com.example.dongdong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.dongdong.R;

/**
 * @author deofly
 * @since 1.0 2014/12/04
 */
public class PrivateSettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_setting);

        findViewById(R.id.message).setOnClickListener(this);
        findViewById(R.id.chat).setOnClickListener(this);
        findViewById(R.id.privacy).setOnClickListener(this);
        findViewById(R.id.general).setOnClickListener(this);
        findViewById(R.id.security).setOnClickListener(this);
        findViewById(R.id.about).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.message:
                intent = new Intent(this, PrivateSettingMessageActivity.class);
                break;
            case R.id.chat:

                break;
            case R.id.privacy:
                intent = new Intent(this, PrivateSettingPrivacyActivity.class);
                break;
            case R.id.general:
                intent = new Intent(this, PrivateSettingGeneralActivity.class);
                break;
            case R.id.security:
                intent = new Intent(this, PrivateSettingSecurityActivity.class);
                break;
            case R.id.about:
                intent = new Intent(this, PrivateSettingAboutActivity.class);
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
