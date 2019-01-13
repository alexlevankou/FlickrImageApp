package by.alexlevankou.flickrimageapp.presenter;

import by.alexlevankou.flickrimageapp.App;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    //Компоненты MVP приложения
    private MainContract.View mView;
    private MainContract.Repository mRepository;

    //Сообщение
    private String message;


    //Обрати внимание на аргументы конструктора - мы передаем экземпляр View, а Repository просто создаём конструктором.
    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onActivityCreated() {
        App.getInstance().getRepository().getAllPosts();
        //mView.showText(message);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
