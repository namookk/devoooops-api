package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevBadRequestException extends DevException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DevBadRequestException(ErrorConst errorConst, Throwable cause) {
        super(errorConst, cause);
    }

    public DevBadRequestException(ErrorConst errorConst) {
        super(errorConst);
    }

    public DevBadRequestException(String errorConst) {
        super(errorConst);
    }

    public DevBadRequestException(String prefix, ErrorConst errorConst, String suffix) {
        super(prefix, errorConst, suffix);
    }







}
