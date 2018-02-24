package com.example.zoran.zqs_databinding_study.observable;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by zqs on 2018/2/6.
 */

public class PlainUser {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();

}
