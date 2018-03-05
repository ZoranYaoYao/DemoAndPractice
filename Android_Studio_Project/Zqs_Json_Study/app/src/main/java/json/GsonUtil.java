package json;

import com.google.gson.Gson;

/**
 * Created by zqs on 2018/2/25.
 */

public class GsonUtil {

    public static String obj2Json(Object object){
        String json = null;
        try {
            json = new Gson().toJson(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T json2Obj(String json, Class<T> clazz){
        T obj = null;
        try{
            obj = new Gson().fromJson(json, clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
