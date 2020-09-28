package ltbd.group.demosupertap.ui.main;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ltbd.group.demosupertap.Base.BasePresenter;
import ltbd.group.demosupertap.api.NetWorkClient;
import ltbd.group.demosupertap.models.MainData;
import retrofit2.Response;

/**
 * Created by ltbd on 9/26/20.
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private Disposable disposable;

    @Override
    public void attachView(MainContract.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }

    @Override
    public void getFetchData() {
        NetWorkClient.create().fetchData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<MainData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Response<MainData> value) {
                        if (value.isSuccessful()) {
                            getView().onFetchDataSuccess(value.body());
                        } else {
                            getView().onFetchDataError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "There was an error loading the." + e.getMessage());
                        getView().onFetchDataError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
