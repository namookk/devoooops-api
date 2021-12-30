package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevNotFoundException extends DevException{
    public DevNotFoundException(ErrorConst errorConst, Throwable cause) {
        super(errorConst, cause);
    }
    public DevNotFoundException(ErrorConst errorConst) {
        super(errorConst);
    }
}
