package roantrevormarcdanieltiffany.com.dawsonbestfinder;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import roantrevormarcdanieltiffany.com.dawsonbestfinder.api.FriendFinder;
import roantrevormarcdanieltiffany.com.dawsonbestfinder.api.OpenWeather;
import roantrevormarcdanieltiffany.com.dawsonbestfinder.beans.Friend;

public class FindFriendsActivity extends MenuActivity
{
    private static final String TAG = FindFriendsActivity.class.getSimpleName();
    ListView friendsLV;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "Called onCreate(Bundle savedInstanceState)");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        friendsLV = findViewById(R.id.friendsListView);
        new FindFriendsAsyncTask().execute();

    }

    public class FindFriendsAsyncTask extends AsyncTask<Void, Void, List<Friend>>
    {
        @Override
        protected List<Friend> doInBackground(Void... voids)
        {
            Log.d(TAG, "Called FindFriendsAsyncTask.doInBackground()");

            // SharedPreferences for the Settings activity are stored in "settings"
            SharedPreferences prefs = getSharedPreferences(SettingsActivity.SETTINGS, MODE_PRIVATE);

            String email = prefs.getString(SettingsActivity.EMAIL, "");
            String password = prefs.getString(SettingsActivity.PASSWORD, "");

            try
            {
                URL url = FriendFinder.buildFindFriendsURL(email, password);
                String json = OpenWeather.getResponseFromHttpUrl(url);
                System.out.println(json);
                return FriendFinder.getFriendsFromJSON(json);
            }
            catch (MalformedURLException e)
            {
                Log.e(TAG, "MalformedURLException in FindFriendsAsyncTask.doInBackground");
            }
            catch (IOException e)
            {
                Log.e(TAG, "IOException in FindFriendsAsyncTask.doInBackground");
            } catch (JSONException e)
            {
                Log.e(TAG, "JSONException in FindFriendsAsyncTask.doInBackground");
            }

            Log.e(TAG, "doInBackground Returning null");
            return null;
        }

        @Override
        protected void onPostExecute(List<Friend> friends)
        {
            Log.d(TAG, "Called FindFriendsAsyncTask.onPostExecute()");
for(int i = 0; i < friends.size(); i++)
{
    System.out.println(friends.get(i).getFirstname());
}
           ArrayAdapter<Friend> adapter = new ArrayAdapter<>(FindFriendsActivity.this, android.R.layout.simple_list_item_1, friends);
System.out.println(adapter.getItem(0));
           friendsLV.setAdapter(adapter);
        }
    }

}