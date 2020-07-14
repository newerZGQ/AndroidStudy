package study.zgq.com.aidlclient;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import study.zgq.com.androidstudy.Person;
import study.zgq.com.androidstudy.PersonManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("study.zgq.com.androidstudy");
                intent.setAction("study.zgq.server");
                bindService(intent,connection, Service.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.tt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setPackage("study.zgq.com.androidstudy");
                intent.setAction("study.zgq.messenger.service");
                bindService(intent,messengerConnection, Service.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.ttt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AUTHORITY = "com.android.zgq.provider";
                Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/student");
                ContentValues contentValues = new ContentValues();
                contentValues.put("id",0);
                contentValues.put("name","peter");
                contentValues.put("gender",0);
                contentValues.put("number","201804081705");
                contentValues.put("score","100");
                getContentResolver().insert(STUDENT_URI, contentValues);
            }
        });
    }

    private Messenger mMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d("nanwei这是从服务端来的", msg.toString());
            return false;
        }
    }));

    private Messenger messenger;

    private ServiceConnection messengerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message message = Message.obtain();
            message.what = 100;
            message.arg1 = 333;
            message.replyTo = mMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PersonManager personManager = PersonManager.Stub.asInterface(service);
            try {
                for (Person person: personManager.getPersonList()) {
                    Log.d("nanwei", person.getName());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
