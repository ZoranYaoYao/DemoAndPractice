package com.example.zoran.zqs_databinding_study.collection;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.SparseArray;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.databinding.ActivityCollectionsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqs on 2018/2/6.
 *
 * DataBinding 绑定集合
 * 0.【Note】
 *       数据结构在xml中的变量需要明确指定变量类型，而不是指定接口
 *        <variable name="map" type="Map"/> Wrong
 *        <variable name="map" type="Map&lt;String,String>"/> Right
 *
 * 1.集合中对于'<'的转义  &lt;
 *
 * 2.List的引用
 *      （1）java文件绑定数据
 *              binding.setList(list);
 *      （2） XML文件申明数据，引用数据
 *              <import type="java.util.List"/>
 *              android:text="@{list[index]}"
 * 3.Map的引用
 *      （1）Java文件绑定数据
 *              binding.setMap(map);
 *
 *      （2）XML文件申明数据，引用数据
 *              <variable name="map" type="Map&lt;String,String>"/>
 *              android:text="@{map.get(key)}"/>
 *       【Note】
 *           数据结构在xml中的变量需要明确指定变量类型，而不是指定接口
 *            <variable name="map" type="Map"/> Wrong
 *            <variable name="map" type="Map&lt;String,String>"/> Right
 * 4.SparseArray的引用
 *       （1）Java文件绑定数据
 *              binding.setSpareArray(sparseArray);
 *       （2）XML文件申明数据，引用数据
 *             <variable name="spareArray" type="SparseArray&lt;String>"/>
 *       【Note】
 *       <variable name="spareArray" type="SparseArray&lt;int,String>"/> Wrong
 *       <variable name="sparse" type="SparseArray&lt;String>"/>
 */
public class CollectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectionsBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_collections);

        List<String> list = new ArrayList<>();
        list.add("Zhu Shanyao");

        Map map = new HashMap();
        String key = "age";
        map.put(key,"22");

        SparseArray sparseArray = new SparseArray();
        sparseArray.put(1,"sparseArray");


        binding.setList(list);
        binding.setIndex(0);
        binding.setMap(map);
        binding.setKey(key);
        binding.setSpareArray(sparseArray);


    }
}


/**
 *  1. < 里面的&lt是什么意思呢？ 为什么我没有箭头效果呢
 *      https://www.imooc.com/qadetail/185979
 *      http://www.w3school.com.cn/html/html_entities.asp
 * */

/**
 * E/AndroidRuntime(20671): FATAL EXCEPTION: main
 E/AndroidRuntime(20671): android.content.res.Resources$NotFoundException: String resource ID #0x0
 E/AndroidRuntime(20671):        at android.content.res.Resources.getText(Resources.java:242)
 E/AndroidRuntime(20671):        at android.support.v7.widget.ResourcesWrapper.getText(ResourcesWrapper.java:53)
 E/AndroidRuntime(20671):        at android.widget.TextView.setText(TextView.java:3805)
 E/AndroidRuntime(20671):        at com.example.zoran.zqs_databinding_study.databinding.ActivityCollectionsBinding.executeBindings(ActivityCollectionsBinding.java:142)
 E/AndroidRuntime(20671):        at android.databinding.ViewDataBinding.executeBindingsInternal(ViewDataBinding.java:379)
 E/AndroidRuntime(20671):        at android.databinding.ViewDataBinding.executePendingBindings(ViewDataBinding.java:351)
 E/AndroidRuntime(20671):        at android.databinding.ViewDataBinding$6.run(ViewDataBinding.java:178)
 E/AndroidRuntime(20671):        at android.databinding.ViewDataBinding$7.doFrame(ViewDataBinding.java:251)
 E/AndroidRuntime(20671):        at android.view.Choreographer$CallbackRecord.run(Choreographer.java:723)
 E/AndroidRuntime(20671):        at android.view.Choreographer.doCallbacks(Choreographer.java:555)
 E/AndroidRuntime(20671):        at android.view.Choreographer.doFrame(Choreographer.java:524)
 E/AndroidRuntime(20671):        at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:711)
 E/AndroidRuntime(20671):        at android.os.Handler.handleCallback(Handler.java:615)
 E/AndroidRuntime(20671):        at android.os.Handler.dispatchMessage(Handler.java:92)
 E/AndroidRuntime(20671):        at android.os.Looper.loop(Looper.java:137)
 E/AndroidRuntime(20671):        at android.app.ActivityThread.main(ActivityThread.java:4921)
 E/AndroidRuntime(20671):        at java.lang.reflect.Method.invokeNative(Native Method)
 E/AndroidRuntime(20671):        at java.lang.reflect.Method.invoke(Method.java:511)
 E/AndroidRuntime(20671):        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1008)
 E/AndroidRuntime(20671):        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:775)
 E/AndroidRuntime(20671):        at dalvik.system.NativeStart.main(Native Method)

    [Solution]
    android:text="@{index}" -》 android:text="@{String.valueOf(index)}"
    http://blog.csdn.net/rodulf/article/details/50908776
 * */

/**
 *Error:Execution failed for task ':app:compileDebugJavaWithJavac'.
 > android.databinding.tool.util.LoggedErrorException: Found data binding errors.
 / data binding error msg:Cannot find the setter for attribute 'android:text' with parameter type V on android.widget.TextView.
 file:D:\Project_WorkSpace\Android_Studio_Project\Zqs_DataBinding_Study\app\src\main\res\layout\activity_collections.xml
 loc:39:28 - 39:39
 *\ data binding error
 *
 *  [Root]
 *  <variable name="map" type="Map"/>
 *  [Solution]
 *  <variable name="map" type="Map&lt;String,String>"/>
 * */