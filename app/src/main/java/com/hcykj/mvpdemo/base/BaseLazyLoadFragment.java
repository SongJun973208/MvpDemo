package com.hcykj.mvpdemo.base;

/**
 * @描述: 延迟加载Fragment基类
 * @作者: 宋俊 SongJun
 * @时间: 2016/10/11 11:48
 */
public abstract class BaseLazyLoadFragment extends BaseFragment {

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

    /**
     * 这里重写此方法主要是为了防止ViewPager中的fragment切换的时候出现隐藏的过度效果
     * @param menuVisible
     */
    @Override
    public void setMenuVisibility(boolean menuVisible) {
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
