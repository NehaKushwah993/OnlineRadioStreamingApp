/*
 * Copyright (C) 2013 YIXIA.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.vov.vitamio.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;


import java.lang.ref.WeakReference;

import io.vov.vitamio.utils.Lg;

public class InitActivity extends Service {
    public static final String FROM_ME = "fromVitamioInitActivity";
    private static final String TAG = InitActivity.class.getSimpleName();
    //  private ProgressDialog mPD;
    private UIHandler uiHandler;

    //  protected void onCreate(Bundle savedInstanceState) {
    @Override
    public void onCreate() {
        super.onCreate();
//    super.onCreate(savedInstanceState);
//    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        uiHandler = new UIHandler(this);

        new AsyncTask<Object, Object, Boolean>() {
            @Override
            protected void onPreExecute() {
//        mPD = new ProgressDialog(InitActivity.this);
//        mPD.setCancelable(false);
//        mPD.setMessage(InitActivity.this.getString(getResources().getIdentifier("vitamio_init_decoders", "string", getPackageName())));
//        mPD.show();
            }


            @Override
            protected void onPostExecute(Boolean inited) {
                if (inited) {
                    uiHandler.sendEmptyMessage(0);
                }
            }


            @Override
            protected Boolean doInBackground(Object... arg0) {
                // TODO Auto-generated method stub
                return null;
            }

        }.execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Lg.v(TAG, "onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Lg.v(TAG, "onStartCommand");
        super.onDestroy();
    }

    private static class UIHandler extends Handler {
        private WeakReference<Context> mContext;

        public UIHandler(Context c) {
            mContext = new WeakReference<Context>(c);
        }

        public void handleMessage(Message msg) {
            InitActivity ctx = (InitActivity) mContext.get();
            switch (msg.what) {
                case 0:
//          ctx.mPD.dismiss();
//          Intent src = ctx.getIntent();
//          Intent i = new Intent();
//          i.setClassName(src.getStringExtra("package"), src.getStringExtra("className"));
//          i.setData(src.getData());
//          i.putExtras(src);
//          i.putExtra(FROM_ME, true);
//          ctx.startActivity(i);
//          ctx.finish();
                    ctx.stopSelf();
                    break;
            }
        }
    }
}
