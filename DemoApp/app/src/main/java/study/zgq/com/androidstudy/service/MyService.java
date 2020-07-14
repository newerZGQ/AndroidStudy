package study.zgq.com.androidstudy.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.Util;
import study.zgq.com.androidstudy.activity.MainActivity;

public class MyService extends Service {

    public MyBinder myBinder = new MyBinder();
    private final static int NOTIFICATION_ID = android.os.Process.myPid();
    public static final String CHANNEL_ID_STRING = "nyd001";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Util.print("---------->>myservice onCreate");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Util.print("---------->>myservice onStartCommand");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID_STRING, "诺秒贷", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
            startForeground(1, notification);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private Notification getNotification()
    {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "")
                .setContentTitle("服务运行于前台")
                .setContentText("service被设为前台进程")
                .setTicker("service正在后台运行...")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Util.print("---------->>myservice onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.print("---------->>myservice onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Util.print("---------->>myservice onUnbind");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Util.print("---------->>myservice onRebind");
        super.onRebind(intent);
    }

    public void test(){
        Util.print("this is a myservice");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Util.print("--------->>myservice onbind");
        return new MyBinder();
    }

    public class MyBinder extends Binder {

        public void testProxy(){
            MyService.this.test();
        }
    }
}
