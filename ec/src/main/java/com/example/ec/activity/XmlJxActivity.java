package com.example.ec.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ec.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class XmlJxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_jx);

        try {
            parseXMLWithPull("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseXMLWithPull(String s) throws Exception{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(s));
        int evetType = parser.getEventType();

    }
}
