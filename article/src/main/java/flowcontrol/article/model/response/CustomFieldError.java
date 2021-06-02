package flowcontrol.article.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CustomFieldError {

    private String field;

    private String message;

}

