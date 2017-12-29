#### 前言
>文章代码示例已放到Github上了,有需要的朋友可以去看下[TDialog](https://github.com/Timmy-zzh/TDialog),欢迎star和fork,项目会一直维护,有疑问可以提Issues或留言.
##### 文章目录
* TDialog框架的由来
* 框架使用解析
* 框架实现原理,用到了那些知识点
* 应用中常见弹窗实现效果
* UML类图
* 总结

###### 正文开始前先来一波效果图
![](/images/TDialog.gif)
#### 一.TDialog的由来
TDialog是继承自DialogFragment进行封装的,大部分开发者在实现弹窗效果的时候,会首选系统提供的AlertDialog;
但是使用系统的Dialog在某些情况下会出现问题,最常见的场景是当手机屏幕旋转时Dialog弹窗会消失,并抛出一个系统,这个异常不会导致异常崩溃,因为Google开发者知道这个问题,并进行了处理.
Dialog使用起来其实更简单,但是Google却是推荐尽量使用DialogFragment.
##### 1.DialogFragment的优点
* DialogFragment 本身是 Fragment 的子类，有着和 Fragment 基本一样的生命周期，使用 DialogFragment 来管理对话框，当旋转屏幕和按下后退键的时候可以更好的管理其生命周期
* 在手机配置变化导致 Activity 需要重新创建时，例如旋转屏幕，基于 DialogFragment 的对话框将会由 FragmentManager 自动重建，然而基于 Dialog 实现的对话框却没有这样的能力
#### 二.使用
##### 1.在项目build.gradle文件中添加依赖
```
compile 'com.timmy.tdialog:tdialog:1.1.1'
```
##### 2.Activity或者Fragment中使用
```
    new TDialog.Builder(getSupportFragmentManager())
            .setLayoutRes(R.layout.dialog_click)
            .setWidth(600)
            .setHeight(800)
            .setScreenWidthAspect(MainActivity.this,0.5f)
            .setScreenHeightAspect(MainActivity.this,0.6f)
            .setTag("DialogTest")
            .setDimAmount(0.6f)
            .setGravity(Gravity.CENTER)
            .setOnBindViewListener(new OnBindViewListener() {
                @Override
                public void bindView(BindViewHolder viewHolder) {
                    bindViewHolder.setText(R.id.tv_content, "abcdef");
                }
            })
            .addOnClickListener(R.id.btn_right, R.id.tv_title)
            .setOnViewClickListener(new OnViewClickListener() {
                @Override
                public void onViewClick(BindViewHolder viewHolder,View view, TDialog tDialog) {
                        switch (view1.getId()) {
                            case R.id.btn_right:
                                Toast.makeText(MainActivity.this, "btn_right", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_title:
                                Toast.makeText(MainActivity.this, "tv_title", Toast.LENGTH_SHORT).show();
                                break;
                        }
                }
            })
            .create()
            .show();
```
##### 使用方法解析
TDialog的实现原理和系统Dialog原理差不多,主要使用Builder设计模式实现
1.创建弹窗,必须传入xml布局文件,且自己设置背景色,因为默认是透明背景色
```
new TDialog.Builder(getSupportFragmentManager())
        .setLayoutRes(R.layout.dialog_click)
        .create()
        .show();
```
2.设置弹窗的宽高(如果不设置宽或者高,默认使用包裹内容的高度)
```
          new TDialog.Builder(getSupportFragmentManager())
            .setLayoutRes(R.layout.dialog_click)
            .setWidth(600)  //设置弹窗固定宽度(单位:px)
            .setHeight(800)//设置弹窗固定高度
            .setScreenWidthAspect(Activity.this,0.5f) //动态设置弹窗宽度为屏幕宽度百分比(取值0-1f)
            .setScreenHeightAspect(Activity.this,0.6f)//设置弹窗高度为屏幕高度百分比(取值0-1f)
            .create()
            .show();
```
3.设置弹窗展示的位置
```
.setGravity(Gravity.CENTER)
```
4.设置弹窗背景色透明度(取值0-1f,0为全透明)
```
.setDimAmount(0.6f)
```
5.当弹窗需要动态改变控件子view内容时,这里借鉴了RecyclerView.Adapter的设计思想,内部封装号一个BindViewHolder
```
.setOnBindViewListener(new OnBindViewListener() {
    @Override
    public void bindView(BindViewHolder viewHolder) {
        bindViewHolder.setText(R.id.tv_content, "abcdef");
    }
})
```
6.监听弹窗子控件的点击事件,只需要将点击事件控件的id传入,并设置回调接口
```
.addOnClickListener(R.id.btn_right, R.id.tv_title)
.setOnViewClickListener(new OnViewClickListener() {
    @Override
    public void onViewClick(BindViewHolder viewHolder,View view, TDialog tDialog) {
        switch (view.getId()) {
            case R.id.btn_right:
                Toast.makeText(DialogEncapActivity.this, "btn_right", Toast.LENGTH_SHORT).show();
                tDialog.dismiss();
                break;
            case R.id.tv_title:
                Toast.makeText(DialogEncapActivity.this, "tv_title", Toast.LENGTH_SHORT).show();
                break;
        }
    }
})
```
