package com.example.inent_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button implicitButton = findViewById(R.id.implicitButton);
        implicitButton.setOnClickListener(this::onClick);

        Button explicitButton = findViewById(R.id.explicitButton);
        explicitButton.setOnClickListener(this::onClick);
    }

    public void onClick(View button) {
        switch (button.getId()) {
            case R.id.explicitButton:
                Intent explicit_intent = new Intent(getBaseContext(), ExplicitActivity.class);
                startActivity(explicit_intent);
                break;

            case R.id.implicitButton:
                TextView implicitData = findViewById(R.id.implicitData);
                String data = implicitData.getText().toString();

                Pattern phone = Pattern.compile("(0/91)?[7-9][0-9]{9}");
                Pattern email = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$");
                Pattern website = Pattern.compile("");

                if(phone.matcher(data).matches()) {
                    Intent implicit_intent = new Intent(Intent.ACTION_DIAL);
                    implicit_intent.setData(Uri.parse("tel:"+data));
                    startActivity(implicit_intent);

                } else if(email.matcher(data).matches()) {
                    Intent implicit_intent = new Intent(Intent.ACTION_SEND);
                    implicit_intent.setData(Uri.parse("mailto:"+data));
                    implicit_intent.putExtra(Intent.EXTRA_SUBJECT, "Sending email using Intents.");
                    implicit_intent.setType("message/rfc822");
                    startActivity(implicit_intent);

                } else if(URLUtil.isValidUrl(data)) {
                    Intent implicit_intent = new Intent(Intent.ACTION_VIEW);
                    implicit_intent.setData(Uri.parse(data));
                    startActivity(implicit_intent);

                } else {
                    Toast.makeText(getApplicationContext(),"Oh no! This app could not find any applications to open this data with!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}