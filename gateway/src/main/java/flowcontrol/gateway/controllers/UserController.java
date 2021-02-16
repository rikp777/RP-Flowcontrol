package flowcontrol.gateway.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@Api(value = "User Rest APi - Endpoints")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(){

    }


    @GetMapping("/me")
    @ApiOperation(value = "Returns the current user profile")
    public ResponseEntity getUserProfile(){
        return ResponseEntity.ok("Hello test");
    }
}
