package flowcontrol.transport.controllers.advise;


import flowcontrol.transport.exception.AppException;
import flowcontrol.transport.exception.PalletLabelException;
import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class TransportAdvise {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processValidationError(MethodArgumentNotValidException exception, WebRequest request){
        BindingResult result = exception.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        String data = processAllErrors(allErrors).stream().collect(Collectors.joining("/n"));
        return new ApiResponse(false, data, exception.getClass().getName(), resolvePathFromWebRequest(request));
    }

    private List<String> processAllErrors(List<ObjectError> allErrors){
        return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
    }

    private String resolveLocalizedErrorMessage(ObjectError objectError){
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        log.info(localizedErrorMessage);
        return localizedErrorMessage;
    }

    private String resolvePathFromWebRequest(WebRequest request){
        try {
            return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
        } catch (Exception ex) {
            return null;
        }
    }

    @ExceptionHandler(value = AppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleAppException(AppException exception, WebRequest request) {
        return new ApiResponse(
                false,
                exception.getMessage(),
                exception.getClass().getName(),
                resolvePathFromWebRequest(request)
        );
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return new ApiResponse(
                false,
                exception.getMessage(),
                exception.getClass().getName(),
                resolvePathFromWebRequest(request)
        );
    }

    @ExceptionHandler(value = PalletLabelException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handlePalletLabelException(ResourceNotFoundException exception, WebRequest request) {
        return new ApiResponse(
                false,
                exception.getMessage(),
                exception.getClass().getName(),
                resolvePathFromWebRequest(request)
        );
    }
}
