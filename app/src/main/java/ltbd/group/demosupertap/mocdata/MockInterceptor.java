package ltbd.group.demosupertap.mocdata;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ltbd.group.demosupertap.R;
import ltbd.group.demosupertap.models.MainData;
import ltbd.group.demosupertap.models.NewFunction;
import ltbd.group.demosupertap.models.Product;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ltbd on 9/26/20.
 */
public class MockInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        String uri = chain.request().url().uri().toString();
        String responseString = mockData();
        return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(ResponseBody.create(MediaType.parse("application/json"),
                        responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }

    /**
     * set up mock data for API
     * @return json data
     */
    public static String mockData() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, R.drawable.icon1, "Cleaning", true));
        products.add(new Product(2, R.drawable.icon2, "Event Assistant", false));
        products.add(new Product(3, R.drawable.icon3, "Office Assistant", false));

        products.add(new Product(4, R.drawable.icon4, "Coffee Delivery", false));
        products.add(new Product(5, R.drawable.icon5, "Food Delivery", false));
        products.add(new Product(6, R.drawable.icon6, "Shopping", true));

        products.add(new Product(7, R.drawable.icon7, "Grocery Delivery", false));
        products.add(new Product(8, R.drawable.icon8, "Messenger", true));
        products.add(new Product(9, R.drawable.icon9, "Bills Payment", false));

        products.add(new Product(10, R.drawable.icon10, "Personal Assistant", false));
        products.add(new Product(11, R.drawable.icon11, "Assistant On Bike", false));
        products.add(new Product(12, R.drawable.icon12, "Queuing Up", false));

        products.add(new Product(13, R.drawable.icon13, "Pet Sitting", false));
        products.add(new Product(14, R.drawable.icon14, "Flyeing", false));
        products.add(new Product(15, R.drawable.icon15, "Dish Washing", false));

        products.add(new Product(16, R.drawable.icon16, "Cash On Delivery", false));
        products.add(new Product(17, R.drawable.icon17, "Deep Clean", false));
        products.add(new Product(18, R.drawable.icon18, "Dish Washing", false));

        products.add(new Product(19, R.drawable.icon19, "Car Wash", false));
        products.add(new Product(20, R.drawable.icon20, "Manicure & Washing", false));

        List<NewFunction> newFunctions = new ArrayList<>();
        newFunctions.add(new NewFunction(1,R.drawable.image2,"How to Use the App","Getting access on demand"));
        newFunctions.add(new NewFunction(1,R.drawable.image1,"List Your Services on my MyKuya","List Your Services on my MyKuya"));

        MainData mainData = new MainData(products,newFunctions);

        return new Gson().toJson(mainData);
    }

}

