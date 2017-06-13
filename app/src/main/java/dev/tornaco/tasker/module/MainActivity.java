package dev.tornaco.tasker.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        final SettingsProvider provider = new SettingsProvider(this);

        final EditText ed = (EditText) findViewById(R.id.editText);
        ed.setText(provider.getTarget());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed.getText().toString();
                provider.setTarget(name);
            }
        });
    }
}
