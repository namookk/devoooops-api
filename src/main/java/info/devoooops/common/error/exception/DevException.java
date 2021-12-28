package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;
import lombok.Getter;

@Getter
public class DevException extends Exception {

    private int code;

    public DevException(ErrorConst errorConst, Throwable cause) {
        super(errorConst.getMsg(), cause);
        this.code = errorConst.getCode();
    }

    public DevException(ErrorConst errorConst) {
        super(errorConst.getMsg());
        this.code = errorConst.getCode();
    }

    public DevException(ErrorConst errorConst, String customMsg) {
        super(customMsg);
        this.code = errorConst.getCode();
    }

    public DevException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
