package com.kits.project.controllers;

import com.kits.project.DTOs.*;
import com.kits.project.exception.BadRequestException;
import com.kits.project.exception.ForbiddenException;
import com.kits.project.model.User;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.interfaces.AccountAuthorityServiceInterface;
import com.kits.project.services.interfaces.EmailServiceInterface;
import com.kits.project.services.interfaces.TicketServiceInterface;
import com.kits.project.services.interfaces.UserServiceInterface;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountAuthorityServiceInterface accountAuthorityServiceInterface;

    @Autowired
    private TicketServiceInterface ticketServiceInterface;

    @Autowired
    private EmailServiceInterface emailServiceInterface;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginDTO, BindingResult errors) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            User account = this.userServiceInterface.findByUsername(loginDTO.getUsername());
            if(!account.isConfirmed())
                throw new ForbiddenException("Account not confirmed!");

            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            Long id = account.getId();
            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details, id, account.getAccountAuthorities()));
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch(ForbiddenException ex) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/check_username",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity checkUsername(
            @RequestParam("username") String username) {

        if (username == null || username.equals(""))
            throw new BadRequestException("Username can't be empty!");

        return new ResponseEntity(this.userServiceInterface.isUsernameTaken(username), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/users/get_all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArrayList<User>> getAllUsers() {

        ArrayList<User> allUsers = userServiceInterface.getAllUsers();

        return new ResponseEntity(allUsers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/users/remove_user",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity removeUser(@RequestBody String username) {
        if (username == null || username.equals(""))
            throw new BadRequestException("Username can't be empty!");

        User account = this.userServiceInterface.findByUsername(username);
        account.setDeleted(true);
        userServiceInterface.save(account);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/current_user/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getCurrentUser(@RequestHeader("Authentication-Token") String token, @PathVariable String username) {
        User account = this.userServiceInterface.findByUsername(username);
        List<String> roles = new ArrayList<>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(
                authority -> roles.add(authority.getAuthority()));

        ProfileDTO responseDTO = new ProfileDTO(account);
        int ticketsBought = this.ticketServiceInterface.getOwnedTickets(token).size();
        responseDTO.setTicketsBought(ticketsBought);

        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/change_profile",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeProfile(@RequestHeader("Authentication-Token") String token, @Valid @RequestBody ChangeProfileDTO changeProfileDTO) {
        try {
            String username = jwtUtils.getUsernameFromToken(token);

            User acc = this.userServiceInterface.findByUsername(username);

            acc.setFirstName(changeProfileDTO.getFirstName());
            acc.setLastName(changeProfileDTO.getLastName());

            if(!changeProfileDTO.getPassword().equalsIgnoreCase("unchanged") && !changeProfileDTO.getNewPassword().equalsIgnoreCase("unchanged")) {
                if (!BCrypt.checkpw(changeProfileDTO.getPassword(), acc.getPassword()))
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

                String newPassword = BCrypt.hashpw(changeProfileDTO.getNewPassword(), BCrypt.gensalt());
                acc.setPassword(newPassword);
            }

            if(changeProfileDTO.getEmail() != acc.getEmail()) {
                try {
                    emailServiceInterface.sendActivationMail(acc);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            this.userServiceInterface.save(acc);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(OptimisticEntityLockException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }


    }
}