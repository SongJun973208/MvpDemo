package com.hcykj.mvpdemo.base;

/**
 * 延迟加载
 * Created by songjun on 2016/11/28.
 */
public abstract class BaseMvpLazyLoadFragment<P extends BasePresenter> extends BaseMvpFragment<P> {
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    /** 标志位，标志已经初始化完成 */
    protected boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    protected boolean mHasLoadedOnce;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /** 可见 */
    protected void onVisible() {
        lazyLoad();
    }

    /** 不可见 */
    protected void onInvisible() {
    }

    /** 延迟加载 子类必须重写此方法 */
    protected abstract void lazyLoad();

}
