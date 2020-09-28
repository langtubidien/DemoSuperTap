package ltbd.group.demosupertap.Base;

/**
 * Created by ltbd on 9/26/20.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private V view;
    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(IView) before" +
                    " requesting data to the Presenter");
        }
    }
}
