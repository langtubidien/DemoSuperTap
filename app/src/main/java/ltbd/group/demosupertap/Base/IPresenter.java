package ltbd.group.demosupertap.Base;


/**
 * Created by ltbd on 9/26/20.
 */
public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();
}
