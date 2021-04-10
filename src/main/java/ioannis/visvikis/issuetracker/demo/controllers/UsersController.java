package ioannis.visvikis.issuetracker.demo.controllers;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import ioannis.visvikis.issuetracker.demo.entities.User;
import ioannis.visvikis.issuetracker.demo.models.requests.AuthenticateUserRequest;
import ioannis.visvikis.issuetracker.demo.models.responses.LogInSignUpResponse;
import ioannis.visvikis.issuetracker.demo.security.SecurityEnum;
import ioannis.visvikis.issuetracker.demo.services.FetchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private FetchService fetchService;



    @GetMapping
    public List<User> getAllUsers(){

        return fetchService.getAllUsers();
    }


    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username){
        return fetchService.getUserByUsername(username);
    }


    @PostMapping
    @RequestMapping("/signup")
    public LogInSignUpResponse signUpUser(@Valid @RequestBody AuthenticateUserRequest signUpUserRequest) {
        //check username is available
        User matchingUser = null;
        try{
            matchingUser = fetchService.getUserByUsername(signUpUserRequest.getUsername());
            throw new ResponseStatusException(HttpStatus.CONFLICT); // Previous line did not throw not found exception, username already taken
        }
        catch (ResponseStatusException rse){
            if(!(rse.getStatus() == HttpStatus.NOT_FOUND)) throw rse; //something other than user not found went wrong, throw and investigate
        }
        User newUSer = new User(signUpUserRequest.getUsername());
        String hashedPassword = BCrypt.hashpw(signUpUserRequest.getPassword(), BCrypt.gensalt(10));
        newUSer.setPassword(hashedPassword);
        fetchService.addUser(newUSer);
        String jwtToken = produceJwtToken(newUSer);
        return new LogInSignUpResponse(jwtToken);
    }


    @PostMapping
    @RequestMapping("/signin")
    public LogInSignUpResponse authenticateUser(@Valid @RequestBody AuthenticateUserRequest signInUserRequest) {
        String submittedUserName = signInUserRequest.getUsername();
        User matchingUser = fetchService.getUserByUsername(submittedUserName);
        validateUserPassword(signInUserRequest.getPassword(), matchingUser.getPassword());
        return new LogInSignUpResponse(produceJwtToken(matchingUser));
    }


    protected void validateUserPassword(String submittedUnhashedPassword, String matchingUserHashedPassword){
        if(!BCrypt.checkpw(submittedUnhashedPassword, matchingUserHashedPassword)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Password");
    }

    protected String produceJwtToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SecurityEnum.API_SECRET_KEY.getEnumValue())
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Long.parseLong(SecurityEnum.TOKEN_VALIDITY_PERIOD_MILLISECONDS_STRING.getEnumValue())))
                .claim(SecurityEnum.CLAIM_USERNAME.getEnumValue(), user.getUsername())
                .compact();
        return token;
    }


}
