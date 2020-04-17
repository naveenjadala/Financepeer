package com.example.financepeer.server;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.financepeer.R;
import com.example.financepeer.model.UserPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {UserPost.class}, version = 1, exportSchema = false)
public abstract class AddDataBase extends RoomDatabase {

    private static Context activity;
    private static final String DATABASE_NAME = "Store_Images";
    private static volatile AddDataBase INSTANCE;
    private static final Object LOCK = new Object();

    public abstract DataDao storeImageDao();

    public static AddDataBase getAddDataBase(Context context) {
        if(context != null) {
            activity = context.getApplicationContext();
            if(INSTANCE == null) {
                synchronized (LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AddDataBase.class, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .addCallback(roomCallBack)
                                .build();
                    }
                }
            }
            return INSTANCE;
        } else {
            return null;
        }
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new SetDataAsyncTask(INSTANCE).execute();
        }
    };


    private static class SetDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private DataDao storeImageDao;
        private SetDataAsyncTask(AddDataBase db) {
            storeImageDao = db.storeImageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fillTheData(activity);
            return null;
        }
    }

    private static JSONArray loadJsonArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream io = context.getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(io));
        String data;
        try {
            while ((data = reader.readLine()) != null) {
                builder.append(data);
            }
            JSONObject jsonObject = new JSONObject(builder.toString());
            return jsonObject.getJSONArray("data");
        }catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void fillTheData(Context context) {
        DataDao dao = getAddDataBase(context).storeImageDao();

        JSONArray data = loadJsonArray(context);
        Log.d("MAINDATADATABASE","DATA:  "+data);

        try {
            for (int i=0;i<data.length();i++) {
                JSONObject jsonObject = data.getJSONObject(i);

                int userId = jsonObject.getInt("userId");
                String title = jsonObject.getString("title");
                String body = jsonObject.getString("body");
                UserPost userPost = new UserPost();
                userPost.setBody(body);
                userPost.setTitle(title);
                userPost.setUserId(userId);

                dao.insert(userPost);
            }

        }catch (JSONException e) {

        }
    }



}
