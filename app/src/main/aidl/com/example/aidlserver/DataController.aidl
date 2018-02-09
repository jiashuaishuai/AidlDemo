// DataController.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements
//导入所需要的非默认支持数据类型包
import com.example.aidlserver.bean.DataBean;

interface DataController {
    //传参数如果是非默认支持数据类型，前需要加tag（in，out，inout）
   void addDataInOut(inout DataBean data);
   //所有的返回值前都不需要家人和修饰符，无论任何数据类型
    List<DataBean> getData();

     void addDataIn(in DataBean data);
     void addDataOut(out DataBean data);
}
