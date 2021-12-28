package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevForbiddenException extends DevException{
    public DevForbiddenException(ErrorConst errorConst, Throwable cause) {
        super(errorConst, cause);
    }
    public DevForbiddenException(ErrorConst errorConst) {
        super(errorConst);
    }
}
