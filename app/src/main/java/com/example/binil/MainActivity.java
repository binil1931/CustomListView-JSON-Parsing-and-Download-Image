package com.example.binil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.binil.adapter.ListAdapter;
import com.example.binil.adapter.ListGetSet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ProgressDialog pgLogin;

    private ProgressDialog pDialog;
    String URL="https://randomapi.com/api/?key=LMW0-SW97-ISC4-FF25&id=t60ldyb&results=20";
    String reply;

    ArrayList<ListGetSet> arraylist = new ArrayList<ListGetSet>();
    ListAdapter adapter;
    ListView listview;

    String[] descritpion;
    String[] picture;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        new MyAsyncTask().execute();  // call async task
        listview = (ListView) findViewById(R.id.list);

        //--------------------------------------------
        //Progress bar
        //--------------------------------------------

        pgLogin = new ProgressDialog(MainActivity.this);
        pgLogin.setMessage("Just a moment please...");
        pgLogin.setIndeterminate(true);
        pgLogin.setCancelable(false);
        pgLogin.setCanceledOnTouchOutside(false);

        pgLogin.show();

    }



    //----------------------------------------------------------------------
// Start Web parsing
//----------------------------------------------------------------------
    private class MyAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // TODO Auto-generated method stub
           // reply = getResponseByParsing(URL);
            //getResponseByParsing(URL);

            reply = "{\"results\": [{\"descritpion\": \"Binil\",  \"picture\": \"https://upload.wikimedia.org/wikipedia/commons/5/58/Sunset_2007-1.jpg\"},{\"descritpion\": \"Vishnu\",  \"picture\": \"http://www.aspen-tech.com/wp-content/uploads/2014/09/Act-CRM-Sunset-Mode.jpg\"},{\"descritpion\": \"abcd\",  \"picture\": \"http://images.boomsbeat.com/data/images/full/17078/sunset_1-jpg.jpg\"},{\"descritpion\": \"efgh\",  \"picture\": \"https://upload.wikimedia.org/wikipedia/commons/2/21/Pink_sunset.jpg\"},{\"descritpion\": \"ijklm\",  \"picture\": \"https://upload.wikimedia.org/wikipedia/commons/1/16/Dinghy_crossing_as_the_sun_set.jpg\"}]}";
            if (reply != null) {
                try {
                    JSONObject jsonObj = new JSONObject(reply);
                    JSONArray results = jsonObj.getJSONArray("results");
                    Log.e("tag ", "length " + results.length());
                    descritpion = new String[results.length()];
                    picture = new String[results.length()];
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject c = results.getJSONObject(i);

                        String cDescritpion = c.getString("descritpion");
                        String cPicture = c.getString("picture");
                        Log.e("Tag ", "descritpion " + cDescritpion);
                        Log.e("Tag ", "cPicture " + cPicture);
                        descritpion[i] = cDescritpion;
                        picture[i] = cPicture;
                        ListGetSet listValue = new ListGetSet(descritpion[i], picture[i]);
                        arraylist.add(listValue);
                        //   listValue.setdescritpion(descritpion);

                        //   detailList.add(listValue);
                    }
                    adapter = new ListAdapter(MainActivity.this, arraylist);
                }
                catch (Exception e) {
                    // TODO: handle exception
                }

            }


            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            if (pgLogin.isShowing()) {
                pgLogin.cancel();
                pgLogin.dismiss();
            }
            Log.e("tag ","set adapter");
            listview.setAdapter(adapter);

        }
    }

    public  String getResponseByParsing(String URL)
    {
        String response_string = null;
        // Build the JSON object to pass parameters
        try {
            HttpClient client = new DefaultHttpClient();
            String getURL = URL;
            HttpGet httpGet = new HttpGet(getURL);
            HttpResponse response = client.execute(httpGet);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                //parse response.

                response_string= EntityUtils.toString(resEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response_string;

    }

//----------------------------------------------------------------------
// End Web parsing
//----------------------------------------------------------------------


    @Override
    public void onBackPressed() {
        finish();
    }
}
