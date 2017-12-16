package test.mindorks.swipe.tinderswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    EditText name, age, imageURL, description, username, password;
    Button accountbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.passWord);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        imageURL = (EditText) findViewById(R.id.url);
        description = (EditText) findViewById(R.id.description);
        accountbtn = (Button) findViewById(R.id.accountbtn);

        accountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jObj = new JSONObject();
                try {

                    jObj.put("name", name.getText().toString());
                    jObj.put("age",  age.getText().toString());
                    jObj.put("url",  imageURL.getText().toString());
                    jObj.put("description",  description.getText().toString());
                    jObj.put("username",  username.getText().toString());
                    jObj.put("password",  password.getText().toString());
                    jObj.put("college", "0");

                    //place jObj into local profiles JSONArray

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    System.out.println("Error:" + e);
                }

            }
        });


    }
}
