package app.ordering.food.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, Object> response(HttpServletResponse response, Exception exception) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "0000001");
        responseBody.put("msg", "invalid json");
        responseBody.put("data", null);
        return responseBody;
    }
}
