package com.example.aidlserver.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JiaShuai on 2018/2/9.
 */

public class DataBean implements Parcelable {
    private String dataName;
    private int dataInt;
    public DataBean(){}

    public DataBean(Parcel in) {
        dataName = in.readString();
        dataInt = in.readInt();
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel in) {
            return new DataBean(in);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataName);
        dest.writeInt(dataInt);
    }

    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        dataName = dest.readString();
        dataInt = dest.readInt();
    }


    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getDataInt() {
        return dataInt;
    }

    public void setDataInt(int dataInt) {
        this.dataInt = dataInt;
    }
}
