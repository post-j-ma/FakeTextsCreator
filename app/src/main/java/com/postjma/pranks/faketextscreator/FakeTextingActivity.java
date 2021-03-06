package com.postjma.pranks.faketextscreator;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FakeTextingActivity extends AppCompatActivity {
    private TextView mPhone;
    private TextView mMsg;
    private TextView mDate;
    private Context mThis;

    private String getText(TextView textView)
    {
        String ret = null;
        try {
            ret = textView.getText().toString();
        }
        catch (Exception e) {
            // ignore
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_texting);
        mThis = this;

        mPhone = (TextView)findViewById(R.id.textView2);
        mMsg = (TextView)findViewById(R.id.textView4);
        mDate = (TextView)findViewById(R.id.textView6);
        Button mButton = (Button)findViewById(R.id.button);

        // Set up the user interaction to manually show or hide the system UI.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = getText(mPhone);
                String msg = getText(mMsg);
                String datestr = getText(mDate);

                ContentValues cv = new ContentValues();
                cv.put("address", phoneNo);
                cv.put("body", msg);
                cv.put("read", 0);
                cv.put("date", datestr);

                getContentResolver().insert(Uri.parse("content://sms/inbox"), cv);

                mPhone.setText("");
                mMsg.setText("");
                mDate.setText("");
            }
        });

    }
}
