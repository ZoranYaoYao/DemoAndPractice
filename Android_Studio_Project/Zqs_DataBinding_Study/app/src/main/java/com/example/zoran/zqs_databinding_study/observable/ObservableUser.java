package com.example.zoran.zqs_databinding_study.observable;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.zoran.zqs_databinding_study.BR;

/**
 * Created by zqs on 2018/2/6.
 */

public class ObservableUser extends BaseObservable{

    private String firstName;

    private String lastName;

    public ObservableUser() {
    }

    public ObservableUser(String firstName, String lastname) {
        this.firstName = firstName;
        this.lastName = lastname;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firsName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }


}
