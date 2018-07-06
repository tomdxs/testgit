package com.safeclass.xhg.xhg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onMainClick(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.loginimg:
                intent = new Intent(this, StartActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}
