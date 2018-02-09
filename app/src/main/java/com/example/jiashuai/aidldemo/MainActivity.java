package com.example.jiashuai.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aidlserver.DataController;
import com.example.aidlserver.bean.DataBean;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    private DataController dataController = null;
    private boolean mBound = false;
    private List<DataBean> mDataBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.example.aidlserver");
        intent.setPackage("com.example.aidlserver");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            dataController = DataController.Stub.asInterface(service);
            mBound = true;
            if (dataController != null) {
                try {
                    mDataBeans = dataController.getData();
                    for (DataBean d :
                            mDataBeans) {
                        Log.e(TAG, "mDataBeans集合    " + d.getDataName() + "    " + d.getDataInt());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            mBound = false;

        }
    };

    public void addBookInOut(View view) {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dataController == null) {
            new NullPointerException("dataController  Null");
            return;
        }

        DataBean dataBean = new DataBean("张三InOut", 0);
        try {
            Log.e(TAG, "客户端发送" + dataBean.getDataName() + "    " + dataBean.getDataInt());
            dataController.addDataInOut(dataBean);
            Log.e(TAG, "客户端发送成功" + dataBean.getDataName() + "    " + dataBean.getDataInt());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookIn(View view) {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dataController == null) {
            new NullPointerException("dataController  Null");
            return;
        }

        DataBean dataBean = new DataBean("张三In", 0);
        try {
            Log.e(TAG, "客户端发送" + dataBean.getDataName() + "    " + dataBean.getDataInt());
            dataController.addDataIn(dataBean);
            Log.e(TAG, "客户端发送成功" + dataBean.getDataName() + "    " + dataBean.getDataInt());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookOut(View view) {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dataController == null) {
            new NullPointerException("dataController  Null");
            return;
        }

        DataBean dataBean = new DataBean("张三Out", 0);
        try {
            Log.e(TAG, "客户端发送" + dataBean.getDataName() + "    " + dataBean.getDataInt());
            dataController.addDataOut(dataBean);
            Log.e(TAG, "客户端发送成功" + dataBean.getDataName() + "    " + dataBean.getDataInt());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            attemptToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
