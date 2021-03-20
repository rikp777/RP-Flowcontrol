package flowcontrol.transport.model.general;

import lombok.Data;

@Data
public class Farmer {
    private Long id;
    private String name;
    private String street;
    private String street_number; //String because 4a 4b
    private String place;
    private String zipCode;
    private String country;
    private String province;
    private String email;
    private String phone;
    private String fax;
    private String kvk;
}
