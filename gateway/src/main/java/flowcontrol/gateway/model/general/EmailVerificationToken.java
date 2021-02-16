package flowcontrol.gateway.model.general;

import flowcontrol.gateway.model.entity.base.DateAudit;

import javax.persistence.*;

public class EmailVerificationToken extends DateAudit {

    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_token_sequence")
    @SequenceGenerator(name = "email_token_sequence", allocationSize = 1)
    private Long id;
}
