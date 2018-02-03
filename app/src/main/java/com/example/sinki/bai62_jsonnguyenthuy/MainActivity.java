package com.example.sinki.bai62_jsonnguyenthuy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sinki.model.Contact;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayList<Contact>dsContact;
    ArrayAdapter<Contact>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvContact = (ListView) findViewById(R.id.lvContact);
        dsContact = new ArrayList<>();
        adapter = new ArrayAdapter<Contact>(MainActivity.this,android.R.layout.simple_list_item_1,dsContact);
        lvContact.setAdapter(adapter);
        ContactTask task = new ContactTask();
        task.execute();
    }

    private class ContactTask extends AsyncTask<Void,Void,ArrayList<Contact>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            super.onPostExecute(contacts);
            adapter.clear();
            adapter.addAll(contacts);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {
            ArrayList<Contact>ds = new ArrayList<>();
            try
            {
                URL url = new URL("https://www.w3schools.com/js/customers_mysql.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line!=null)
                {
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONArray jsonArray = new JSONArray(builder.toString());
                for (int i = 0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Contact contact = new Contact();
                    if(jsonObject.has("Name"))
                        contact.setName(jsonObject.getString("Name"));
                    if(jsonObject.has("City"))
                        contact.setCity(jsonObject.getString("City"));
                    if(jsonObject.has("Country"))
                        contact.setCountry(jsonObject.getString("Country"));
                    ds.add(contact);
                }
            }
            catch (Exception ex)
            {
                Log.e("LOI",ex.toString());
            }
            return ds;
        }
    }
}
