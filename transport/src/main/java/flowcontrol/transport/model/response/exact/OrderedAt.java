package flowcontrol.transport.model.response.exact;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@Builder
public class OrderedAt {

    @XmlAttribute(name="test")
    private Creditor creditor;
    private String date;
}
