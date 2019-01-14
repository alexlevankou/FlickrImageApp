package by.alexlevankou.flickrimageapp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

abstract class BasePresenter<View> extends ViewModel implements LifecycleObserver {

    protected View view = null;
    private Lifecycle viewLifecycle = null;

    public void attachView(View view, Lifecycle viewLifecycle) {
        this.view = view;
        this.viewLifecycle = viewLifecycle;
        viewLifecycle.addObserver(this);
    }

    protected View view() {
        return view;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onViewDestroyed() {
            view = null;
            viewLifecycle = null;
    }
}