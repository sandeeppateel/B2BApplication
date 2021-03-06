package com.example.harish.b2bapplication.activity;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.harish.b2bapplication.R;
import android.view.LayoutInflater;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by harish on 6/3/16.
 */
public class StoreAck {
    String filename = "acktoken";

    FileOutputStream outputStream;
    FileInputStream inputStream;
    View rootView;
   
    public void writeFile(Context c, JSONObject jObj)
    {
        try {
            String ack = jObj.getString("token");
            String roll = jObj.getString("roll");
            String  email = jObj.getString("email");
            outputStream = c.openFileOutput(filename, c.MODE_PRIVATE);
            outputStream.write(ack.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(email.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(roll.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
            Log.d("<<<<<<<<<<<<<<<<<<<<<", "Ack in file");



        }catch (Exception e){e.printStackTrace();}


    }

    public String[] readFile(Context c)
    {
        try {
            inputStream = c.openFileInput(filename);
            BufferedReader buffer = new BufferedReader( new InputStreamReader(inputStream));
            String line  [] = new String[3];
            String temp = null;
            int i = 0;
            while((temp=buffer.readLine())!= null )
            {
                line[i] = temp;
                i++;
            }

            Log.d(line.toString(), ">>>>>>>>>>>>>>>>>>>>>>Ack in file");

            inputStream.close();
            return line;



        }catch (IOException e){

            e.printStackTrace();
            return null;

        }


    }

    public void DeleteFile(Context c)
    {

            boolean flag = c.deleteFile(filename);
            if (flag == true) {
                Log.d("true", ">>>>>>>>>>>>>>>>>>>>>>Ack in deleted");
            } else {
                Log.d("false", ">>>>>>>>>>>>>>>>>>>>>>Ack in deleted");
            }

    }



}
