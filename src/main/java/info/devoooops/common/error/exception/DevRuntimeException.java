package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevRuntimeException extends DevException{

    public DevRuntimeException(ErrorConst errorConst, Throwable cause) {
        super(errorConst, cause);
    }
    public DevRuntimeException(ErrorConst errorConst) {
        super(errorConst);
    }
    public DevRuntimeException(ErrorConst errorConst, String customMsg) {
        super(errorConst, customMsg);
    }
    public DevRuntimeException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
