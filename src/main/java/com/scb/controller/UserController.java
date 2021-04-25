package com.scb.controller;

import com.scb.dto.OrderRequest;
import com.scb.dto.OrderResponse;
import com.scb.dto.UserRequest;
import com.scb.dto.UserResponse;
import com.scb.model.Account;
import com.scb.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final TokenStore tokenStore;
    private final ModelMapper modelMapper;
    @GetMapping("/users")
    public ResponseEntity<UserResponse> users() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> additionalInfo = getAdditionalInfo(authentication);
        String userName = (String) additionalInfo.get("user_name");
        Account userInfo = userService.getUserInfo(userName);
        UserResponse userResponse = modelMapper.map(userInfo, UserResponse.class);
        List<Long> anotherType = userInfo.getOrder()
                .stream()
                .map(t -> t.getBookId())
                .collect(Collectors.toList());
        userResponse.setBooks(anotherType);
        return ResponseEntity.ok(userResponse);
    }

    private Map<String, Object> getAdditionalInfo(Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }



    @PostMapping("/users")
    public Long addUsers(@RequestBody UserRequest userRequest) {
        Account user =modelMapper.map(userRequest,Account.class );
        return userService.addUUser(user);
    }

    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> additionalInfo = getAdditionalInfo(authentication);
        String userName = (String) additionalInfo.get("user_name");
        userService.removeUser(userName);
        return ResponseEntity.ok("");
    }

    @PostMapping("/users/orders")
    public ResponseEntity<?> order(@RequestBody OrderRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> additionalInfo = getAdditionalInfo(authentication);
        String userName = (String) additionalInfo.get("user_name");
        Double totalAmt = userService.bookOrder(userName, request.getOrders());
        return ResponseEntity.ok(new OrderResponse(totalAmt));
    }
}
