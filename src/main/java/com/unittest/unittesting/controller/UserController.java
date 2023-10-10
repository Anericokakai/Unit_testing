package com.unittest.unittesting.controller;

import com.unittest.unittesting.Exceptions.UserNotFoundException;
import com.unittest.unittesting.Services.UserService;
import com.unittest.unittesting.model.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
   private final UserService userService;
    @Autowired
    private  final ModelMapper modelMapper;

//    ! CREATE A NEW USER API END POINT
@PostMapping("/new")
    public ResponseEntity<?> saveNewUser(@RequestBody @Valid Users requst){


   Users userData= userService.saveUser(requst);

    URI uri= URI.create("/users/"+userData.getId());
   return ResponseEntity.created(uri).body(userData);



 }



// ! FIND USER BY ID API END POINT
 @GetMapping("/user")
    public ResponseEntity<?> findUserByEmail(@RequestParam  String email){

        var foundUser=userService.findUserByEmail(email);
        return  ResponseEntity.status(200).body(foundUser);



 }


// ! GET ALL BOOKS API END POINT
 @GetMapping("/all")
    public List<Users >findAllUsers(){

    return  userService.findAllUsers();
 }

// !UPDATE USER RECORD

    @PutMapping("/update")

    public  ResponseEntity<?> updateUserDetails(@RequestBody Users updateUser){
try
{
Users updatedUser =userService.updateUser(updateUser);
return ResponseEntity.status(HttpStatus.CREATED.value()).body(updatedUser);


    }catch (UserNotFoundException ex){
    return ResponseEntity.status(401).body(ex.getMessage());



    }

    }


}
