package com.example.teddy.festec.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.latte.activities.ProxyActivity;
import com.example.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        Log.e("ExampleActivity", "setRootDelegate: ");
        return new ExampleDelegate();

    }
}
