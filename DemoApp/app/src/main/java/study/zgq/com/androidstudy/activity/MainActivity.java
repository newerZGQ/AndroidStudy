package study.zgq.com.androidstudy.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.List;

import study.zgq.com.androidstudy.BlankFragment;
import study.zgq.com.androidstudy.service.MyService;
import study.zgq.com.androidstudy.MyThread;
import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.Util;

public class MainActivity extends AppCompatActivity {
    BlankFragment blankFragment1;
    BlankFragment blankFragment2;
    HashMap map = new HashMap(6);
    {
        map.entrySet().iterator();
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Util.print("------------>>MainActivity onSaveInstanceState");
        outState.putString("nanweiSave", "this is a save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        Util.print("------------>>MainActivity onRestoreInstanceState: " + savedInstanceState.get("nanweiSave"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Util.print("------------>>MainActivity onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Util.print("------------>>MainActivity onRestart");
    }

    @Override
    protected void onStart() {
        getMainLooper();
        super.onStart();
//        Util.print("------------>>MainActivity onStart");
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Util.print("bind success");
            if (service instanceof MyService.MyBinder){
                ((MyService.MyBinder) service).testProxy();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.get("nanweiSave") != null){
//            Util.print("------------>>MainActivity onCreate" + savedInstanceState.get("nanweiSave"));
        }else {
//            Util.print("------------>>MainActivity onCreate");
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        blankFragment1 = BlankFragment.newInstance("","");
        getSupportFragmentManager().beginTransaction().add(0, blankFragment1).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.print(Thread.currentThread().getName());
                blankFragment2 = BlankFragment.newInstance("","");
                getSupportFragmentManager().beginTransaction().add(0, blankFragment2).commit();
                new MyThread(new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Util.print(msg.toString());
                        return false;
                    }
                })).start();
            }
        });

        findViewById(R.id.c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.AppTask> tasks =  manager.getAppTasks();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MyService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //android8.0以上通过startForegroundService启动service
                    startForegroundService(intent);
//                    startService(intent);
                }
            }
        });
        findViewById(R.id.e).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });
        findViewById(R.id.d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
        findViewById(R.id.f).setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        Util.print("------------>>MainActivity onNewIntent");
    }

    @Override
    protected void onPause() {
//        Util.print("------------>>MainActivity onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Util.print("------------>>MainActivity onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Util.print("------------>>MainActivity onStop");
    }

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
