package com.example.lab12security.Controller;

import com.example.lab12security.Model.User;
import com.example.lab12security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body("User Registered");
    }

    @GetMapping("/get-All-Users")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity userUpdate(@RequestBody @Valid User newUser,@AuthenticationPrincipal User user){
        authService.updateUser(newUser,user.getId());
        return ResponseEntity.status(200).body("User updated");
    }


    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }


    @PostMapping("/logout")
    public ResponseEntity logOut(){
        return ResponseEntity.status(200).body("logged out");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body("User Deleted");
    }

}
