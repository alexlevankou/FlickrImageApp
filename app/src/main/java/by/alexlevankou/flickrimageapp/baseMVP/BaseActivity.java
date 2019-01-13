package by.alexlevankou.flickrimageapp.baseMVP;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends AppCompatActivity implements BaseContract.View, LifecycleRegistryOwner {

    private final LifecycleRegistry lifecycleRegistry =
            new LifecycleRegistry(this);
    protected P presenter;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseViewModel<V, P> viewModel =
                ViewModelProviders.of(this).get(BaseViewModel.class);
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(initPresenter());
        }
        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
    }

    protected abstract P initPresenter();
}
