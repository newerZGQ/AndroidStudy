package study.zgq.com.androidstudy;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MessengerService extends Service {
    public MessengerService() {
    }

    private Messenger messenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Util.print("ggg这是从客户端来的" + msg.toString());
            if (msg.what == 100){
                Message message = Message.obtain();
                message.what = 200;
                try {
                    message.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }));

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
