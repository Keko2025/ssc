package receive;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.soho.ssc.ui.activities.DetailActivity;
import com.soho.ssc.ui.activities.MainActivity;
import com.soho.ssc.utils.L;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * @author dell
 * @data 2018/1/3.
 */

public class ShowNotificationReceiver extends BroadcastReceiver{
    private static final String TAG = "MyReceiver";
    private NotificationManager nm;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        //设置点击通知栏的动作为启动另外一个广播
//        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.
//                getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setContentTitle("这就是通知的头")
//                .setTicker("这是通知的ticker")
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(android.R.drawable.ic_lock_idle_charging);
//
//        Log.i("repeat", "showNotification");
//        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(2, builder.build());
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        L.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + bundle.toString());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            L.d(TAG, "JPush用户注册成功");
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            L.d(TAG, "接受到推送下来的自定义消息");
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            L.d(TAG, "接受到推送下来的通知");
            receivingNotification(context,bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            L.d(TAG, "用户点击打开了通知");

            openNotification(context,bundle);

        } else {
            L.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle){
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        L.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        L.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        L.d(TAG, "extras : " + extras);
    }

    private void openNotification(Context context, Bundle bundle){
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            myValue = extrasJson.optString("myKey");
        } catch (Exception e) {
            L.e("Unexpected: extras is not a valid json");
            return;
        }

        L.e("TYPE_THIS"+myValue);
        L.e("TYPE_ANOTHER:"+myValue);

        Intent mainIntent = new Intent(context, MainActivity.class);
        //将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
        //如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
        //如果Task栈不存在MainActivity实例，则在栈顶创建
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra("title", bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
        detailIntent.putExtra("price", bundle.getString(JPushInterface.EXTRA_EXTRA));
        detailIntent.putExtra("detail", bundle.getString(JPushInterface.EXTRA_ALERT));

//        Intent[] intents = {mainIntent, detailIntent};

        context.startActivity(detailIntent);






//        if (TYPE_THIS.equals(myValue)) {
//            Intent mIntent = new Intent(context, ThisActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        } else if (TYPE_ANOTHER.equals(myValue)){
//            Intent mIntent = new Intent(context, AnotherActivity.class);
//            mIntent.putExtras(bundle);
//            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mIntent);
//        }
    }
}
