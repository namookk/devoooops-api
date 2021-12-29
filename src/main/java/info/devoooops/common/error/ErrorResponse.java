package info.devoooops.common.error;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private int code;

    private String msg;

    private String detail;

    @Builder
    public ErrorResponse(boolean isDebug, int code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        if(isDebug) {
            this.detail = detail;
        }
    }
}
