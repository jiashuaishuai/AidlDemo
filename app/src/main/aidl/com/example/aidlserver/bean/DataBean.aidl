// DataBean.aidl
//这个文件的作用是引入了一个序列化对象 DataBean 供其他的AIDL文件使用
//注意：这个DataBean.aidl包名必须和java文件夹下的DataBean.java包名一致
package com.example.aidlserver.bean;

// Declare any non-default types here with import statements
//parcelable 小写p
parcelable DataBean;
