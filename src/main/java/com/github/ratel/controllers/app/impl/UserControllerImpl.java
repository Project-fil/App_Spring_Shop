package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.app.interfaces.UserController;
import com.github.ratel.entity.FileEntity;
import com.github.ratel.entity.User;
import com.github.ratel.handlers.FileHandler;
import com.github.ratel.payload.request.UserUpdateRequest;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.services.UserService;
import com.github.ratel.utils.transfer_object.UserTransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/app/shop/")
@RequiredArgsConstructor
public class UserControllerImpl implements ApiSecurityHeader, UserController {

    private final UserService userService;

    private final FileHandler fileHandler;

    @Override
    @CrossOrigin("*")
    public ResponseEntity<UserResponse> getCurrentUser(Principal principal) {
        User user = this.userService.getCurrentUser(principal);
        return ResponseEntity.ok(UserTransferObj.fromUser(user));
    }

    @Override
    @CrossOrigin("*")
    @Transactional
    public ResponseEntity<UserResponse> update(UserUpdateRequest updateRequest, MultipartFile image) {
        User user = this.userService.findById(updateRequest.getId());
        UserTransferObj.updateUser(user, updateRequest);// test for save address
        FileEntity fileEntity = null;
        if (Objects.nonNull(image)) {
            fileEntity = this.fileHandler.writeFile(image);
        }
        user.setFileEntity(fileEntity);
        return ResponseEntity.ok(UserTransferObj.fromUser(this.userService.updateUser(user)));
    }

    @Override
    @CrossOrigin("*")
    public void deleteUser(Long userId) {
        this.userService.deleteUserById(userId);
    }
}
