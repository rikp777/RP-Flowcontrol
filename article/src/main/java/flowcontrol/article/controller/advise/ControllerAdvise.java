package flowcontrol.article.controller.advise;

import flowcontrol.article.exception.BadRequestException;
import flowcontrol.article.exception.GeneralException;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.response.ApiResponse;
import flowcontrol.article.model.response.CustomFieldError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import reactor.util.annotation.Nullable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class ControllerAdvise {
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processValidationError(MethodArgumentNotValidException exception, WebRequest request) {
        var result = exception.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String data = processAllErrors(allErrors).stream().collect(Collectors.joining("\n"));

        return new ApiResponse( false, data, exception.getClass().getName(), resolvePathFromWebRequest(request));
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
        var currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        log.info(localizedErrorMessage);
        return localizedErrorMessage;
    }

    private String resolvePathFromWebRequest(WebRequest request) {
        try {
            return ((ServletWebRequest)request).getRequest().getRequestURI();
        } catch (Exception ex) {
            return null;
        }
    }

    //region Resource exception handlers
    /**
     * Resourece not found exception handler
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return new ApiResponse(false, exception.getMessage(), exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
    //endregion

    //region Application exception handlers
    /**
     * General exception handler
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = GeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleAppException(GeneralException exception, WebRequest request) {
        return new ApiResponse( false, exception.getMessage(), exception.getClass().getName(), resolvePathFromWebRequest(request));
    }

    /**
     * Bad request exception handler
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleBadRequestException(BadRequestException exception, WebRequest request) {
        return new ApiResponse( false, exception.getMessage(), exception.getClass().getName(), resolvePathFromWebRequest(request));
    }


    @ExceptionHandler(value= BindException.class)
    protected ApiResponse handleBindException(
            BindException exception, WebRequest request, HttpServletResponse response, @Nullable Object handler)
            throws IOException {

        final var defaultMessage = "Validation error";

        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final ArrayList<CustomFieldError> customFieldErrors = new ArrayList<>();

        for(FieldError fieldError : fieldErrors) {
            final String field = fieldError.getField();
            final String message = fieldError.getDefaultMessage();
            final var customFieldError = CustomFieldError.builder().field(field).message(message).build();
            customFieldErrors.add(customFieldError);
        }
        log.info(defaultMessage + " " + customFieldErrors.toString());

        return new ApiResponse( false, defaultMessage, customFieldErrors, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }
}
