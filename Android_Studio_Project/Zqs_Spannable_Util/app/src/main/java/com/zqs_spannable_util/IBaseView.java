package com.zqs_spannable_util;

import android.os.Bundle;
import android.view.View;

/**
 * Created by zqs on 2018/3/30.
 */

interface IBaseView extends View.OnClickListener{

    void initData(final Bundle bundle);

    int bindLayout();

    void initView(final Bundle saveInstancestate, final View view);

    void doBusiness();

    void onWidgetClick(final View view);
}
