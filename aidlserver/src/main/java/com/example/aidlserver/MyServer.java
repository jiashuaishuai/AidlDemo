package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.aidlserver.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiaShuai on 2018/2/9.
 */

public class MyServer extends Service {
    public final String TAG = this.getClass().getSimpleName();
    private List<DataBean> mDataBeans = new ArrayList<>();


    private final DataController.Stub dataController = new DataController.Stub() {
        @Override
        public void addDataInOut(DataBean data) throws RemoteException {
            if (data != null) {
                Log.e(TAG, "服务端接收" + data.getDataName() + "    " + data.getDataInt());
                data.setDataInt(1222);//直接修改客户端传过来的值
                Log.e(TAG, "服务端修改后" + data.getDataName() + "    " + data.getDataInt());
                mDataBeans.add(data);
            }
        }

        @Override
        public List<DataBean> getData() throws RemoteException {
            return mDataBeans;
        }

        @Override
        public void addDataIn(DataBean data) throws RemoteException {
            if (data != null) {
                Log.e(TAG, "服务端接收" + data.getDataName() + "    " + data.getDataInt());
                data.setDataInt(1333);//直接修改客户端传过来的值
                Log.e(TAG, "服务端修改后" + data.getDataName() + "    " + data.getDataInt());
                mDataBeans.add(data);
            }
        }

        @Override
        public void addDataOut(DataBean data) throws RemoteException {
            if (data != null) {
                Log.e(TAG, "服务端接收" + data.getDataName() + "    " + data.getDataInt());
                data.setDataInt(1444);//直接修改客户端传过来的值
                Log.e(TAG, "服务端修改后" + data.getDataName() + "    " + data.getDataInt());
                mDataBeans.add(data);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        DataBean dataBean = new DataBean();
        dataBean.setDataName("这是服务器自己添加的");
        dataBean.setDataInt(11111);
        mDataBeans.add(dataBean);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return dataController;
    }
}
