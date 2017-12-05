package roantrevormarcdanieltiffany.com.dawsonbestfinder.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import roantrevormarcdanieltiffany.com.dawsonbestfinder.beans.Friend;

public class FriendFinder
{
    private static final String TAG = FriendFinder.class.getSimpleName();
    private static final String ERROR_MESSAGE = "cod";
    private static final String LIST_KEY = "list";
    private static final String MAIN_KEY = "main";
    private static final String FIRSTNAME_KEY = "firstname";
    private static final String LASTNAME_KEY = "lastname";
    private static final String EMAIL_KEY = "email";


    private String email;
    private String password;
    private String find_friends_url = "friendfinder02.app/api/api/allfriends?email=" + email + "&password=" + password;

    private static final String API_RESULT_FORMAT = "json";

    public FriendFinder()
    {
        Log.d(TAG, "Instance of FriendFinder initiated.");
    }

    public static List<Friend> getFriendsFromJSON(String jsonResponse) throws JSONException
    {
        List<Friend> friendsList = new ArrayList<>();
        JSONObject json = new JSONObject(jsonResponse);

        if (json.has(ERROR_MESSAGE))
        {
            int errorCode = json.getInt(ERROR_MESSAGE);

            switch (errorCode)
            {
                case HttpURLConnection.HTTP_OK:
                    Log.e(TAG, "Successful Call to Forecast API");
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    Log.e(TAG, "Could not find location");
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    Log.e(TAG, "Wrong APP ID");
                    break;
                default:
                    Log.e(TAG, "Something is wrong on their side");
                    break;
            }
        }

        JSONArray friendsJSONArray = json.getJSONArray(LIST_KEY);

        for (int i = 0; i < friendsJSONArray.length(); i++)
        {
            // Get JSON object representing the day
            JSONObject jsonObject = friendsJSONArray.getJSONObject(i);

            Friend friend = parseJSONToFriend(jsonObject);
            friendsList.add(friend);
        }

        return friendsList;
    }

    private static Friend parseJSONToFriend(JSONObject friendJSON) throws JSONException
    {
        Friend friend = new Friend();

        JSONObject jsonObject = friendJSON.getJSONObject(MAIN_KEY);

        if(jsonObject.has(FIRSTNAME_KEY) && jsonObject.has(LASTNAME_KEY) && jsonObject.has(EMAIL_KEY))
        {
            friend.setFirstname(jsonObject.getString(FIRSTNAME_KEY));
            friend.setLastname(jsonObject.getString(LASTNAME_KEY));
            friend.setEmail(jsonObject.getString(EMAIL_KEY));
        }

        return friend;
    }
}
