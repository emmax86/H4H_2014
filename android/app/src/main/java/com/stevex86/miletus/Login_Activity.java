package com.stevex86.miletus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * A login screen that offers login via email/password.

 */
public class Login_Activity extends Activity {

    private Button enterButton;
    private Button showLicenseButton;
    private CheckBox checkBox;
    private TextView license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        enterButton = (Button) findViewById(R.id.goButton);
        showLicenseButton = (Button) findViewById(R.id.licenseAgreement);
        checkBox = (CheckBox) findViewById(R.id.agreementCheckBox);

    }

    public void popupLicense(View view) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://stevex86.com:5000/license_consultee");
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(responseBody)
                    .setTitle("License Agreement");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void goButtonClick(View view) {
        if(!checkBox.isChecked())
        {
            Toast.makeText(this, "You must agree to the EULA.", Toast.LENGTH_LONG).show();
            return;
        }
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://stevex86.com:5000/create_room");
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);
            Toast.makeText(this, responseBody, Toast.LENGTH_LONG).show();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



