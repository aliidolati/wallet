package com.example.authentication.Controller;

import com.example.authentication.Model.dto.PasswordRequest;
import com.example.authentication.Model.dto.RegisterRequest;
import com.example.authentication.Model.dto.ResetPasswordRequest;
import com.example.authentication.Model.entity.User;
import com.example.authentication.Model.enums.Role;
import com.example.authentication.convertor.BaseConvertor;
import com.example.authentication.exception.ServiceException;
import com.example.authentication.service.RegisterationService;
import com.example.authentication.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/home")
@RequiredArgsConstructor
public class UserController {
    private final BaseConvertor<User , RegisterRequest> convertor ;
    private final RegisterationService registerationService ;
    private final ResetPasswordService resetPasswordService ;
    @PostMapping("/signup")
    public String register(@RequestBody RegisterRequest request) throws ServiceException {
        request.setRole(Role.USER);
        return registerationService.register(convertor.convertDto(request));
    }
    @GetMapping( "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerationService.confirmToken(token);
    }

}
