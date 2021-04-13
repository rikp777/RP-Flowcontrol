package flowcontrol.gateway.model.general;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, String> model;

    public Mail() {
        model = new HashMap<>();
    }
}
