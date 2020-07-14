package study.zgq.com.androidstudy;


import android.os.Handler;
import android.os.Message;

public class MyThread extends Thread {

    private Handler handler;
    public MyThread(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        Message message = handler.obtainMessage();
        message.what = 1;
        message.setTarget(handler);
        message.arg1 = 2;
        handler.post(new Runnable() {
            @Override
            public void run() {
                Util.print(currentThread().getName());
            }
        });
    }
}
