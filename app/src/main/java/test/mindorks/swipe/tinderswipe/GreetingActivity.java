package test.mindorks.swipe.tinderswipe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GreetingActivity extends AppCompatActivity {

    Button logIn = (Button) findViewById(R.id.logbtn);
    Button signUp = (Button) findViewById(R.id.signbtn);
    EditText username = (EditText) findViewById(R.id.userName);
    EditText password = (EditText) findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.equals("")&&password.equals("")){

                Intent intent = new Intent (GreetingActivity.this, MainActivity.class);
                startActivity(intent);}

                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Username or password is incorrect";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent (t)
            }
        });
    }
}
