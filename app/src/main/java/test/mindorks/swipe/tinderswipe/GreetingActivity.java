package test.mindorks.swipe.tinderswipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GreetingActivity extends AppCompatActivity {

    Button logIn;
    Button signUp;
    EditText usernamet;
    String username;
    EditText passwordt;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        logIn = (Button) findViewById(R.id.logbtn);
        signUp = (Button) findViewById(R.id.signbtn);
        usernamet = (EditText) findViewById(R.id.userName);
        username = usernamet.getText().toString();
        passwordt = (EditText) findViewById(R.id.password);
        password = passwordt.getText().toString();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context1 = GreetingActivity.this.getApplicationContext();
                for(Profile profile : Utils.loadProfiles(context1)) {

                    if (username.equals(profile.getUsername()) && password.equals(profile.getPassword())) {

                        Intent intent = new Intent(GreetingActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                Context context = getApplicationContext();
                CharSequence text = "Username or password is incorrect";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GreetingActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
