package com.example.zoran.zqs_databinding_study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zoran.zqs_databinding_study.basic.BasicActivity;
import com.example.zoran.zqs_databinding_study.collection.CollectionActivity;
import com.example.zoran.zqs_databinding_study.conversions.ConversionsActivity;
import com.example.zoran.zqs_databinding_study.custombinding.CustomBindingActivity;
import com.example.zoran.zqs_databinding_study.dynamic.DynamicActivity;
import com.example.zoran.zqs_databinding_study.include.IncludeActivity;
import com.example.zoran.zqs_databinding_study.observable.ObservableActivity;
import com.example.zoran.zqs_databinding_study.resource.ResourceActivity;
import com.example.zoran.zqs_databinding_study.viewstub.ViewStubActivity;
import com.example.zoran.zqs_databinding_study.viewwithids.ViewWithIDsActivity;

/**
 * refer:
 * https://github.com/LyndonChin/MasteringAndroidDataBinding
 *
 * https://www.jianshu.com/p/5d6132e6dc14
 *
 * Skill:
 *   1.新写的xml文件有绑定对象，使用 project -》 rebuild之后，可以自动完成
 *   2.编译错误，请查看 Gradle Console 里面的详细log！
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBasic(View view) {
        startActivity(new Intent(this, BasicActivity.class));
    }

    public void openCustomBinding(View view) {
        startActivity(new Intent(this, CustomBindingActivity.class));
    }

    public void openIncludes(View view) {
        startActivity(new Intent(this, IncludeActivity.class));
    }

    public void openCollections(View view) {
        startActivity(new Intent(this, CollectionActivity.class));
    }

    public void openResources(View view) {
        startActivity(new Intent(this, ResourceActivity.class));
    }

    public void openObservable(View view) {
        startActivity(new Intent(this, ObservableActivity.class));
    }

    public void openViewWithIDs(View view) {
        startActivity(new Intent(this, ViewWithIDsActivity.class));
    }

    public void openViewStub(View view) {
        startActivity(new Intent(this, ViewStubActivity.class));
    }

    public void openDynamicVariables(View view) {
        startActivity(new Intent(this, DynamicActivity.class));
    }

    public void openAttributeSetters(View view) {
        //startActivity(new Intent(this, AttributeSettersActivity.class));
    }

    public void openConverters(View view) {
        startActivity(new Intent(this, ConversionsActivity.class));
    }
}
