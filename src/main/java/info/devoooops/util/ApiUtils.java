package info.devoooops.util;

import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.ErrorResponse;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;


public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(HttpStatus.OK, "정상적으로 처리되었습니다.", response);
    }

    public static ApiResult<?> error(ErrorConst errorConst, String details) {
        return new ApiResult<>(errorConst.getCode(), errorConst.getMsg(), null, details);
    }

    public static ApiResult<?> error(HttpStatus status, String message, String details) {
        return new ApiResult<>(status, message, null, details);
    }

    public static ApiResult<?> error(ErrorResponse errorResponse) {
        return new ApiResult<>(errorResponse.getCode(), errorResponse.getMsg(), null, errorResponse.getDetail());
    }

    @Getter
    public static class ApiResult<T> {
        private final int code;
        private final String message;
        private final T response;
        private final String error;

        private ApiResult(HttpStatus status, String message, T response) {
            this(status.value(), message, response, null);
        }

        private ApiResult(HttpStatus status, String message, T response, String error) {
            this(status.value(), message, response, error);
        }

        private ApiResult(int code, String message, T response, String error) {
            this.code = code;
            this.message = message;
            this.response = response;
            this.error = error;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("code", code)
                    .append("message", message)
                    .append("response", response)
                    .toString();
        }
    }

}