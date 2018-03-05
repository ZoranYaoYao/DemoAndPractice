package bean;

import java.io.Serializable;

/**
 * Created by herr.wang on 2017/6/14.
 */

public class BaseHttpResp implements Serializable {

    public int code;
    public String message;

    public String toString() {
        return "code = "+ code +", message = " + message;
    }

}
