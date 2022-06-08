 package com.example.androids_xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

    private XmlPullParserFactory factory;
    private XmlPullParser parser;

    private Map<String, SingleModuleConfig> allModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
            InputStream is = getApplicationContext().getAssets().open("test.xml");
            parser.setInput(is, "UTF-8");

            startParse(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startParse(XmlPullParser parser) throws XmlPullParserException, IOException {
        int event = parser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG) {

            }

            event = parser.next();
        }
    }

    private class SingleModuleConfig {
        private boolean isModuleEnabled;

        public boolean getModuleSwitch() {
            return isModuleEnabled;
        }

        public void parseConfig(XmlPullParser parser) {

        }
    }

    private void parseModuleConfig(XmlPullParser parser) throws XmlPullParserException, IOException {
        int event = parser.getEventType();
        SingleModuleConfig config;

        do {
            if (event == XmlPullParser.START_TAG && "Module".equals(parser.getName())) {
                // start
                String moduleName = parser.getAttributeValue(null, "name");
                config = allModules.get(moduleName);

                // next
                event = parser.next();
            }
        } while (event != XmlPullParser.END_TAG || !"Module".equals(parser.getName()));

    }
}