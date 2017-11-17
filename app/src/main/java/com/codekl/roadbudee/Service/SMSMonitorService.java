package com.codekl.roadbudee.Service;

/**
 * Updated by MonsieurPetion on 2017-11-15.
 */
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSMonitorService extends Service {
    private static final String TAG = "SMSMonitorService";

    private void sendReplySMS(String phoneNumber){

        String response = "I'm driving with RoadBudee.I'll see your message when I get where I'm going";

        sendSMS(phoneNumber,response);
    }

    private final BroadcastReceiver receiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG,"WORKS");
            if(action.equals("android.provider.Telephony.SMS_RECEIVED")) {
                Log.i(TAG,"SMS Broadcast Received");
                Bundle bundle = intent.getExtras();
                SmsMessage[] messages = null;

                if (bundle != null) {
                    Object [] pdus = (Object[]) bundle.get("pdus");
                    messages = new SmsMessage[pdus.length];
                    for(int i = 0; i < messages.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        sendReplySMS(messages[i].getOriginatingAddress());
                    }
                }
            }
        }
    };

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Log.i(TAG,"message sent");
        } catch (Exception ex) {
            Log.i(TAG,ex.getMessage().toString());
            ex.printStackTrace();
        }
    }

    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"SMS Monitor service started");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receiver1, filter);
        Log.i(TAG,"Broadcast Receiver registered");
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver1);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

}