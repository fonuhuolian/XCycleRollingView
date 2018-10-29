# XCycleRollingView

> 添加依赖

`root build.gradle `
```
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
`module build.gradle `
```
implementation 'com.github.fonuhuolian:XCycleRollingView:1.0.9'
```

> 混淆
```
-dontwarn org.fonuhuolian.cyclerollingview.**
-keep class org.fonuhuolian.cyclerollingview.**{*;}
```

> xml

```
<org.fonuhuolian.cyclerollingview.XCycleRollingView
    android:id="@+id/xv"
    android:layout_width="wrap_content"
    android:layout_height="30dp"
    app:autoScrollInterval="4000" />
```

> 代码

```
xCycleRollingView = (XCycleRollingView) findViewById(R.id.xv);

// 清空滚动条目
xCycleRollingView.clearAllViews();
// 添加滚动条目
for (int i = 0; i < 5; i++) {
    View inflate = View.inflate(this, R.layout.a, null);
    xCycleRollingView.addItemView(inflate);
}

// 解决重影（对应的生命周期调用对应的方法）
xCycleRollingView.onResume();
xCycleRollingView.onPause();
```

> 效果

![效果](https://github.com/fonuhuolian/XCycleRollingView/blob/master/screenshot/a.jpg?raw=true)
