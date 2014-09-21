package com.stevex86.miletus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;


/**
 * A login screen that offers login via email/password.

 */
public class Login_Activity extends Activity {

    private Button enterButton;
    private Button showLicenseButton;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        enterButton = (Button) findViewById(R.id.goButton);
        showLicenseButton = (Button) findViewById(R.id.licenseAgreement);
        checkBox = (CheckBox) findViewById(R.id.agreementCheckBox);
    }

    public void goButtonClick(View view) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://stevex86.com:5000/create_room");
        try {
            HttpResponse response = client.execute(post);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



