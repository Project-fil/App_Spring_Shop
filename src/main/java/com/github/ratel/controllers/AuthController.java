package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.Address;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.Roles;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.UnverifiedException;
import com.github.ratel.exceptions.statuscode.StatusCode;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.UserAuthRequest;
import com.github.ratel.payload.response.TokenResponse;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.security.UserDetailsImpl;
import com.github.ratel.services.AddressService;
import com.github.ratel.services.SendGridMailService;
import com.github.ratel.services.UserService;
import com.github.ratel.services.VerificationTokenService;
import com.github.ratel.utils.CheckUtil;
import com.github.ratel.utils.EmailText;
import com.github.ratel.utils.transfer_object.UserTransferObj;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/app/shop/")
@RequiredArgsConstructor
public class AuthController {

    private final EmailText emailText;

    private final CheckUtil checkUtil;

    private final UserService userService;

    private final AddressService addressService;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final SendGridMailService sendGridMailService;

    private final VerificationTokenService verificationTokenService;

    @Transactional()
    @PostMapping("free/create/admin")
    public ResponseEntity<Object> registrationAdmin(@RequestBody @Valid CreateAdminRequest payload) {
        this.checkUtil.checkUserByEmail(payload.getEmail());
        String pass = this.checkUtil.checkPassAndConfirmPass(payload.getPassword(), payload.getConfirmPassword());
        User user = new User();
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.setEmail(payload.getEmail());
        user.setPassword(this.passwordEncoder.encode(pass));
        user.setRoles(Roles.ROLE_ADMIN);
        user.setVerification(UserVerificationStatus.UNVERIFIED);
        var token = (UUID.randomUUID().toString());
        Address address = new Address();
        address.setPhone(payload.getPhone());
        address = this.addressService.create(address);
        user.setAddress(address);
        this.userService.save(user);
        this.verificationTokenService.create(new VerificationToken(user, token));
        this.sendGridMailService.sendMessage(
                user.getEmail(),
                "Верификация пользователя App_Shop",
                this.emailText.regLetter(user.getFirstname(), user.getLastname(), token)
        );
        return ResponseEntity.ok(UserTransferObj.fromUser(user));
    }

    @PostMapping("free/registration")
    public UserResponse createUser(UserDto payload) {
//        this.userService.checkUserByEmail(payload.getEmail());
//        User user = new User();
//        user.setFirstname(payload.getFirstname());
//        user.setLastname(payload.getLastname());
//        user.setEmail(payload.getEmail());
//        String userPass = this.userService.checkPassAndConfirmPass(payload.getPassword(), payload.getConfirmPassword());
//        user.setPassword(this.passwordEncoder.encode(userPass));
//        Address address = new Address();
//        address.setPhone(payload.getPhone());
//        user.setAddress(address);
////        user.setPhone(payload.getPhone());
//        user.setRoles(Roles.ROLE_USER);
//        user.setVerification(UserVerificationStatus.UNVERIFIED);
//        var userToken = UUID.randomUUID().toString();
//        var userPattern = String.format(
//                this.textMessageEmail,
//                payload.getFirstname() + " " + payload.getLastname(),
//                "верификации ",
//                this.verificationDomain,
//                "/verification?token=", userToken);
//        this.emailService.sendMessageToEmail(
//                payload.getEmail(),
//                "Верификация пользователя App_Shop",
//                userPattern
//        );
//        this.userService.save(user);
//        this.verificationTokenService.create(new VerificationToken(user, userToken));
//        return UserTransferObject.fromUser(user);
        return null;
    }
//    public ResponseEntity<Object> registrationManager(@RequestBody @Valid UserDto payload) {
//            return ResponseEntity.ok(this.userService.createUser(payload));
//    }

    @GetMapping("free/verification")
    public ResponseEntity<Object> passingVerification(@RequestParam("token") String token) {
        VerificationToken vt = verificationTokenService.findByToken(token);
        User user = vt.getUser();
        this.userService.updateUser(user.verificationUser(UserVerificationStatus.VERIFIED));
        return ResponseEntity.ok(new TokenResponse(
                this.tokenProvider.generateToken(UserDetailsImpl.fromUserToCustomUserDetails(user)),
                user.getId(),
                user.getFirstname(),
                user.getRoles()
        ));
    }

    @PostMapping("free/authorization")
    public ResponseEntity<Object> auth(@RequestBody @Valid UserAuthRequest userAuthRequest) {
        User user = this.userService.findByEmailAndPassword(userAuthRequest.getEmail(), userAuthRequest.getPassword());
        if (user.getVerification().equals(UserVerificationStatus.UNVERIFIED)) {
            throw new UnverifiedException(StatusCode.USER_NOT_VERIFIED);
        } else {
            return ResponseEntity.ok(new TokenResponse(
                    this.tokenProvider.generateToken(UserDetailsImpl.fromUserToCustomUserDetails(user)),
                    user.getId(),
                    user.getFirstname(),
                    user.getRoles()
            ));
        }
    }

}
