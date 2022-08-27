package stocktradingsimulator.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such stock exists")
public class StockNotFoundException extends Exception {
    public StockNotFoundException(String message) {
        super(message);
    }
}
