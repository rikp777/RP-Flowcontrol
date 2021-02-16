package flowcontrol.gateway.controllers;


import flowcontrol.gateway.controllers.advise.AuthControllerAdvice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);
}
