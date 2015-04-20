/**
 * @Title: MReceiver.java
 * @Package com.cooler.receiver
 * @Copyright: mmxcoo Co., Ltd
 * @Description: TODO
 * @author XJ
 * @date 2015-3-13 上午9:46:50
 * @version V1.0
 */
package com.cooler.receiver;

import com.cooler.service.DaemonService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @ClassName: MReceiver
 * @Description: TODO
 * @author XJ
 * @date 2015-3-13 上午9:46:50 
 *
 */
public class MReceiver extends BroadcastReceiver{

    /* (非 Javadoc)
    * <p>Title: onReceive</p>
    * <p>Description: </p>
    * @param context
    * @param intent
    * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
    */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Log.e("onReceive", "onReceive");
            Toast.makeText(context, "接收到广播，启动service", Toast.LENGTH_SHORT).show();
            context.startService(new Intent(context, DaemonService.class));
        }
    }

}
