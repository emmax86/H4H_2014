package com.stevex86.miletus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Create_Account_Activity extends Activity {

    private String username;
    private String password;
    private String confirmedPassword;
    private String aKey;

    EditText inputUsername;
    EditText inputPassword;
    EditText inputConfirmPassword;
    EditText inputAuthenticationKey;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        registerButton = (Button)findViewById(R.id.register_button);
        inputUsername = (EditText)findViewById(R.id.create_username);
        inputPassword = (EditText)findViewById(R.id.create_password);
        inputConfirmPassword = (EditText)findViewById(R.id.confirm_password);
        inputAuthenticationKey = (EditText)findViewById(R.id.authentication_key);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean errors = false;
                username = inputUsername.getText().toString();
                aKey = inputAuthenticationKey.getText().toString();
                password = inputPassword.getText().toString();
                confirmedPassword = inputConfirmPassword.getText().toString();
                if(!password.matches("[A-Za-z0-9]+"))
                {
                    errors = true;
                }
                if(!password.equals(confirmedPassword))
                {
                    errors = true;
                }
                if(!username.matches("[A-Za-z0-9]+"))
                {
                    errors = true;
                }
                if(!aKey.matches("[A-Za-z0-9\\-]"))
                {
                    errors = true;
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create__account_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
