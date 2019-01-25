package com.kits.project.controllers;

import com.kits.project.DTOs.AccountCreateDTO;
import com.kits.project.DTOs.UserDTO;
import com.kits.project.exception.ForbiddenException;
import com.kits.project.model.AccountAuthority;
import com.kits.project.model.Authority;
import com.kits.project.model.User;
import com.kits.project.services.interfaces.AccountAuthorityServiceInterface;
import com.kits.project.services.interfaces.AuthorityServiceInterface;
import com.kits.project.services.interfaces.EmailServiceInterface;
import com.kits.project.services.interfaces.UserServiceInterface;
import com.kits.project.util.MessageConstants;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import static com.kits.project.util.ConvertDTOToModel.convertAccountCreateDTOToUser;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api")
public class UserRegistrationController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private AuthorityServiceInterface authorityServiceInterface;

    @Autowired
    private AccountAuthorityServiceInterface accountAuthorityServiceInterface;

    @Autowired
    private EmailServiceInterface emailServiceInterface;

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity registerAccount(
            @Valid @RequestBody AccountCreateDTO accountCreateDTO,
            BindingResult errors) {
        try {
            Boolean canRegister = false;
            Boolean isAdmin = false;

            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if (authority.getAuthority().equals(MessageConstants.ADMIN_ROLE))
                    isAdmin = true;
                if (authority.getAuthority().equals("ROLE_ANONYMOUS"))
                    canRegister = true;
            }

            if (!canRegister && !isAdmin)
                throw new ForbiddenException("You need to logout to register or you need to be admin.");

            this.userServiceInterface.checkUsername(accountCreateDTO.getLoginAccount().getUsername());

            User account = new User(accountCreateDTO.getLoginAccount().getUsername(), accountCreateDTO.getLoginAccount().getPassword());
            //Mapiranje istoimenih atributa iz DTO objekta na objekat koji se snima u bazu
            User acc = convertAccountCreateDTOToUser(accountCreateDTO);
            //System.out.println(acc);
            //System.out.println(account);
            //if(isAdmin)
            //    acc.setConfirmed(true);
            //else
            //    acc.setConfirmed(false);
            account.setFirstName(acc.getFirstName());
            account.setLastName(acc.getLastName());
            account.setEmail(acc.getEmail());


            Authority authority = this.authorityServiceInterface.findByName("USER");

            AccountAuthority accountAuthority = new AccountAuthority(account, authority);
            account.getAccountAuthorities().add(accountAuthority);
            try {
                emailServiceInterface.sendActivationMail(account);
            } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/ catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            account = this.userServiceInterface.save(account);

            accountAuthority.setAccount(account);
            accountAuthority.setAuthority(authority);
            this.accountAuthorityServiceInterface.save(accountAuthority);
            return new ResponseEntity<>(new UserDTO(account), HttpStatus.CREATED);
        } catch(OptimisticEntityLockException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(
            value = "/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> activateAccount(@RequestParam("activationId") String activationId) {
        User account = userServiceInterface.findByActivationId(activationId);
        account.setConfirmed(true);
        boolean successActivate = false;
        System.out.println(account.getUsername());
        if(account != null)
            if(account.isConfirmed())
                successActivate = true;
        System.out.println(account.isConfirmed());
        userServiceInterface.save(account);
        return new ResponseEntity<>(successActivate, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/register_controller",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity registerAccountController(
            @Valid @RequestBody AccountCreateDTO accountCreateDTO,
            BindingResult errors) {
        try {
            Boolean canRegister = false;
            Boolean isAdmin = false;

            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if (authority.getAuthority().equals(MessageConstants.ADMIN_ROLE))
                    isAdmin = true;
                if (authority.getAuthority().equals("ROLE_ANONYMOUS"))
                    canRegister = true;
            }

            if (!canRegister && !isAdmin)
                throw new ForbiddenException("You need to logout to register or you need to be admin.");

            this.userServiceInterface.checkUsername(accountCreateDTO.getLoginAccount().getUsername());

            User account = new User(accountCreateDTO.getLoginAccount().getUsername(), accountCreateDTO.getLoginAccount().getPassword());
            //Mapiranje istoimenih atributa iz DTO objekta na objekat koji se snima u bazu
            User acc = convertAccountCreateDTOToUser(accountCreateDTO);
            account.setFirstName(acc.getFirstName());
            account.setLastName(acc.getLastName());
            account.setEmail(acc.getEmail());
            account.setPasswordChanged(false);


            Authority authority = this.authorityServiceInterface.findByName("CONTROLLER");

            AccountAuthority accountAuthority = new AccountAuthority(account, authority);
            account.getAccountAuthorities().add(accountAuthority);
            try {
                emailServiceInterface.sendActivationMail(account);
            } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/ catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            account = this.userServiceInterface.save(account);

            accountAuthority.setAccount(account);
            accountAuthority.setAuthority(authority);
            this.accountAuthorityServiceInterface.save(accountAuthority);
            return new ResponseEntity<>(new UserDTO(account), HttpStatus.CREATED);
        } catch(OptimisticEntityLockException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
