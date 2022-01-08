package info.devoooops.common.error;

import info.devoooops.common.error.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorExceptionHandler {

    /**
     * 400 에러
     * @param e
     */
    @ExceptionHandler(value = {DevBadRequestException.class})
    private ResponseEntity<ErrorResponse> handleBadRequestError(DevBadRequestException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 400 에러 (request body error)
     * @param e
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    private ResponseEntity<ErrorResponse> handleBadRequestError(HttpMessageNotReadableException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.MISSING_REQUIRED_PARAMETER.getCode())
                .msg(ErrorConst.MISSING_REQUIRED_PARAMETER.getMsg())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 400 에러 (request parameters binding error)
     * @param e
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    private ResponseEntity<ErrorResponse> handleBadRequestError(ServletRequestBindingException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.MISSING_REQUIRED_PARAMETER.getCode())
                .msg(ErrorConst.MISSING_REQUIRED_PARAMETER.getMsg())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 400 요청에러(@Valid 에러)
     * @param e
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    private ResponseEntity<ErrorResponse> handleBadRequestValidError(MethodArgumentNotValidException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.INVALID_PARAMETER_ERROR.getCode())
                .msg(e.getBindingResult().getFieldError().getDefaultMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 400 요청에러(@Validated 에러)
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    private ResponseEntity<ErrorResponse> handleBadRequestValidatedError(ConstraintViolationException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.INVALID_PARAMETER_ERROR.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * 401 인증에러
     * @param e
     */
    @ExceptionHandler(value = {DevUnauthorizedException.class})
    private ResponseEntity<ErrorResponse> handleUnauthorized(DevUnauthorizedException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    /**
     * 400 로그인에러
     * @param e
     */
    @ExceptionHandler(value = {BadCredentialsException.class})
    private ResponseEntity<?> handleBadCredentialsError(BadCredentialsException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.BAD_CREDENTIAL.getCode())
                .msg(ErrorConst.BAD_CREDENTIAL.getMsg())
                .detail(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * 403 권한 에러
     * @param e
     */
    @ExceptionHandler(value = {DevForbiddenException.class})
    private ResponseEntity<ErrorResponse> handleForbiddenError(DevForbiddenException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    /**
     * 404 에러
     * @param e
     */
    @ExceptionHandler(value = {DevNotFoundException.class})
    private ResponseEntity<ErrorResponse> handleNotFoundError( DevNotFoundException e){
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception().getStackTrace())))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * 500 서버 에러
     * @param e
     */
    @ExceptionHandler(value = {DevInternalServerErrorException.class})
    private ResponseEntity<ErrorResponse> handleInternalServerError(DevException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString(new Exception().getStackTrace()))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * 500 서버 에러
     * @param e
     */
    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<?> handleInternalServerError(Exception e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.UNKNOWN_ERROR.getCode())
                .msg(ErrorConst.UNKNOWN_ERROR.getMsg())
                .detail(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
