package ltbd.group.demosupertap.ui.products;

import ltbd.group.demosupertap.Base.IPresenter;
import ltbd.group.demosupertap.Base.IView;
import ltbd.group.demosupertap.models.MainData;

/**
 * Created by ltbd on 9/26/20.
 */
public interface HomeContract {
    interface View extends IView {
        void onFetchDataSuccess(MainData products);

        void onFetchDataError();
    }

    interface Presenter extends IPresenter<View> {
        void getFetchData();
    }
}
