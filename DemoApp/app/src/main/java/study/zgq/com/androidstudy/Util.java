package study.zgq.com.androidstudy;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class Util {
    public static void print(String s){
        Log.d("nanwei", s);
    }
    public static void printActivities(AppCompatActivity activity){
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTasks =  activityManager.getAppTasks();
        Util.print("total tasks: " + appTasks.size()+ "\n");
        for (int i = 0; i < appTasks.size(); i++) {
            String base = appTasks.get(i).getTaskInfo().baseActivity.getClassName().split("\\.")[4];
            String top = appTasks.get(i).getTaskInfo().topActivity.getClassName().split("\\.")[4];
            String str = "numActivities: " + appTasks.get(i).getTaskInfo().numActivities +
                    " affiliatedTaskId: " + appTasks.get(i).getTaskInfo().affiliatedTaskId +
                    " baseActivity: " + base +
                    " topActivity: " + top + "\n";
            print(str);
        }
        Util.print("================================>>>>>>>启动分隔\n");
    }
}
