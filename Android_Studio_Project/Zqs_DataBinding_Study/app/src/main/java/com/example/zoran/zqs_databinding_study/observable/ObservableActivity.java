package com.example.zoran.zqs_databinding_study.observable;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableMap;
import android.os.Bundle;
import android.view.View;

import com.example.zoran.zqs_databinding_study.BaseActivity;
import com.example.zoran.zqs_databinding_study.R;
import com.example.zoran.zqs_databinding_study.databinding.ActivityObservableBinding;

/**
 * Created by zqs on 2018/2/6.
 *
 * 1. Observable Obeject 运用
 *      （1） 创建一个继承baseObservable类的数据对象
 *              class ObservableUser extends BaseObservable{}
 *           get属性方法 添加注释
 *                  @Bindable
 *                  public String getFirstName()
 *           set设置方法添加手动刷新方法
 *                  notifyPropertyChanged(BR.lastName);
 *      （2） java文件绑定对象，XML申明对象。运用对象
 *
 * 2. ObservableFiled 引用： 当数据少时
 *       (1) 创建一个变量 带有observableFiled的变量
 *              public final ObservableField<String> firstName = new ObservableField<>();
 *       (2） java文件绑定对象，XML申明对象。运用对象
 *
 *       【Note】
 *       针对带有observableFiled的变量设置值 是用过的set方法
 *          plainUser.firstName.set("Zhu");
 *
 * 3. ObservableArrayMap 运用
 *      (1) 创建ObservableArrayMap 对象 并初始化
 *            ObservableArrayMap observableMap =new ObservableArrayMap();
 *      (2） java文件绑定对象，XML申明对象。运用对象
 *
 *      【Note】
 *       ObservableArrayMap 与 HashMap 有区别
 *       HashMap 在XML中获取的是方式是：              android:text="@{map.get(key)}"/>
 *       ObservableArrayMap 在XML中获取的是方式是：   android:text="@{(observableMap[`firstName`]}"/>
 */

public class ObservableActivity extends BaseActivity {
    ObservableUser observableUser;
    PlainUser plainUser;
    ObservableArrayMap observableMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityObservableBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_observable);
        observableUser = new ObservableUser("Zhu","Shanyao");
        plainUser = new PlainUser();
        observableMap = new ObservableArrayMap();
        setZYSInfo(null);

        binding.setUser(observableUser);
        binding.setPlainUser(plainUser);
        binding.setObservableMap(observableMap);

    }

    public void setZYSInfo(View view) {
        observableUser.setFirstName("Zhu");
        observableUser.setLastName("Shanyao");

        plainUser.firstName.set("Zhu");
        plainUser.lastName.set("Shanyao");
        plainUser.age.set(22);

        observableMap.put("firstName","Zhu");
        observableMap.put("lastName","Shanyao");
        observableMap.put("age",22);
    }

    public void setZQSInfo(View view) {
        observableUser.setFirstName("Z");
        observableUser.setLastName("QS");

        plainUser.firstName.set("Z");
        plainUser.lastName.set("QS");
        plainUser.age.set(27);

        observableMap.put("firstName","Z");
        observableMap.put("lastName","QS");
        observableMap.put("age",27);
    }
}


/**
 * Error:Execution failed for task ':app:compileDebugJavaWithJavac'.
 > android.databinding.tool.util.LoggedErrorException: Found data binding errors.
 / data binding error msg:Identifiers must have user defined types from the XML file. PlainUser is missing it
 file:D:\Project_WorkSpace\Android_Studio_Project\Zqs_DataBinding_Study\app\src\main\res\layout\activity_observable.xml
 loc:30:47 - 30:55
 loc:30:66 - 30:74
 \ data binding error


 * */