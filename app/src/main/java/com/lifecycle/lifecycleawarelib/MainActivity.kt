package com.lifecycle.lifecycleawarelib

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lifecycle.lib.permission.IPermissionResult
import com.lifecycle.lib.permission.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_permission.setOnClickListener {
            if(!PermissionManager.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)) {
                PermissionManager.get(this).requestPermissions(Manifest.permission.RECORD_AUDIO, object : IPermissionResult{
                    override fun granted() {
                        Toast.makeText(this@MainActivity, "允许录音权限", Toast.LENGTH_SHORT).show()
                    }

                    override fun deny(isForever: Boolean) {
                        if(isForever) {
                            Toast.makeText(this@MainActivity, "录音权限需要去设置打开", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@MainActivity, "录音权限被拒绝了", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}
