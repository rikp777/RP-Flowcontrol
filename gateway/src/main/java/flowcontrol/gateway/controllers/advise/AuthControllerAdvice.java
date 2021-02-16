package flowcontrol.gateway.controllers.advise;

import flowcontrol.gateway.exception.*;
import flowcontrol.gateway.model.response.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthControllerAdvice {

    private static final Logger logger = LogManager.getLogger(AuthControllerAdvice.class);

    private final MessageSource messageSource;

    @Autowired
    public AuthControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processValidationError(MethodArgumentNotValidException exception, WebRequest request) {
        BindingResult result = exception.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String data = processAllErrors(allErrors).stream().collect(Collectors.joining("\n"));

        return new ApiResponse(data, false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }

    /**
     * Utility Method to generate localized message for a list of field errors
     *
     * @param allErrors the field errors
     * @return the list
     */
    private List<String> processAllErrors(List<ObjectError> allErrors) {
        return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
    }

    /**
     * Resolve localized error message. Utility method to generate a localized error
     * message
     *
     * @param objectError the field error
     * @return the string
     */
    private String resolveLocalizedErrorMessage(ObjectError objectError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        logger.info(localizedErrorMessage);
        return localizedErrorMessage;
    }

    private String resolvePathFromWebRequest(WebRequest request) {
        try {
            return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
        } catch (Exception ex) {
            return null;
        }
    }

    //region Resource exception handlers
    /**
     * Resource already in use exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = ResourceAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }

    /**
     * Resourece not found exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    //endregion

    //region Application exception handlers

    /**
     * General exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = GeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleAppException(GeneralException exception, WebRequest request) {
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }

    /**
     * Bad request exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleBadRequestException(BadRequestException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    //endregion

    //region Password exception handlers
    /**
     * Password reset link exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = PasswordResetLinkException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handlePasswordResetLinkException(PasswordResetLinkException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    /**
     * Password reset exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = MailSendException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handlePasswordResetException(PasswordResetException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }

    /**
     * Password update exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = PasswordUpdateException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handlePasswordUpdateException(PasswordUpdateException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    //endregion

    //region Token exception handlers
    /**
     * Token invalid exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = TokenInvalidRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiResponse handleTokenInvalidException(TokenInvalidRequestException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    /**
     * Token refresh exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleTokenRefreshException(TokenRefreshException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    //endregion

    //region User exception handlers

    /**
     * User registration exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = UserRegistrationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUserRegistrationException(UserRegistrationException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    /**
     * User logout exception handler
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = UserLogoutException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ApiResponse handleUserLogoutException(UserLogoutException exception, WebRequest request){
        return new ApiResponse(exception.getMessage(), false, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    //endregion
}
