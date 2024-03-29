package com.lifecycle.lib;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lifecycle.lib.activity.ActivityResultDelivery;
import com.lifecycle.lib.activity.IActivityResultRegistry;
import com.lifecycle.lib.permission.IPermissionResultRegistry;
import com.lifecycle.lib.permission.PermissionsResultDelivery;


/**
 * desc: 空白的Fragment，在生命周期方法内通知所有注册对象
 * @author sunxianglei@myhexin.com
 * @date 2019/4/19.
 */

public class LifecycleManagerFragment extends Fragment {

    private ActivityFragmentLifecycle lifecycle;
    private PermissionsResultDelivery permissionsResultCall;
    private ActivityResultDelivery activityResultCall;

    public LifecycleManagerFragment(){
        this.lifecycle = new ActivityFragmentLifecycle();
        this.permissionsResultCall = new PermissionsResultDelivery();
        this.activityResultCall = new ActivityResultDelivery();
    }

    public Lifecycle getMyLifecycle(){
        return lifecycle;
    }

    public IPermissionResultRegistry getPermissionsResultCall(){
        return permissionsResultCall;
    }

    public IActivityResultRegistry getActivityResultCall(){
        return activityResultCall;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycle.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
        lifecycle.removeListener(null);
        permissionsResultCall.removeListener(null);
        activityResultCall.removeListener(null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsResultCall.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        activityResultCall.onActivityResult(requestCode, resultCode, data);
    }
}
