# LifecycleAwareLib
需要感知生命周期的功能的封装库。



## 一、生命周期监听

示例：

```
public class DemoActivity extends Activity {
	
	private Test test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LifecycleDetector.getInstance().observeLifecycle(this, test);
	}
	
	@Override
	protected void onDestroy(){
	    super.onDestroy();
	    LifecycleDetector.getInstance().removeLifecycle(this, test);
	}

}

// 可以监听到生命周期的对象
public class Test implementation LifecycleListener{
	
	@Override
	public void onCreate(){
	}
	
	@Override
	public void onDestroy(){
	}
}
```



### 二、权限申请使用

示例：

```
PermissionManager.get(this).requestPermissions(Manifest.permission.RECORD_AUDIO	, object : IPermissionResult{
        override fun granted() {
            // 允许权限
        }
        override fun deny(isForever: Boolean) {
            if(isForever) {
                // 永久禁止
            }else{
                // 
            }
        }
})
```



### 三、activityResult 回调

示例：

```
ActivityResultManager.get(this).startActivityResult(intent, (isOk, data)-> {
	if(isOk){
	   // 回调成功，处理data
	}else {}
})
```



## 四、原理

对生命周期可感知库做了内部实现的解释：[LifecycleAwareLib原理](https://www.yuque.com/kakasi/xlzyhv/gzgwgz)

后期可拓展更多和生命周期相关的功能。

