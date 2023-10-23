package com.unittest.unittesting.controller;

import com.unittest.unittesting.Exceptions.UserExistException;
import com.unittest.unittesting.Exceptions.UserNotFoundException;
import com.unittest.unittesting.Services.UserServiceImpl;
import com.unittest.unittesting.model.Users;
import com.unittest.unittesting.tdo.UserResponse;
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
   private final UserServiceImpl userServiceImpl;
    @Autowired
    private  final ModelMapper modelMapper;

//    ! CREATE A NEW USER API END POINT
@PostMapping("/new")
    public ResponseEntity<?> saveNewUser(@RequestBody @Valid Users requst) throws UserExistException {


   Users userData= userServiceImpl.saveUser(requst);
   UserResponse userResponse= modelMapper.map(userData,UserResponse.class);

    URI uri= URI.create("/users/"+userData.getId());
   return ResponseEntity.created(uri).body(userResponse);

 }

// ! find user by id

    @GetMapping("/{userId}")

    public ResponseEntity<?> findUserById(@PathVariable("userId") int id) throws Exception{
    Users user= userServiceImpl.findUserById(id);

    return ResponseEntity.status(200).body(user);

    }



// ! FIND USER BY ID API END POINT
 @GetMapping("/user")
    public ResponseEntity<?> findUserByEmail(@RequestParam  String email){

        var foundUser= userServiceImpl.findUserByEmail(email);

     UserResponse response=modelMapper.map(foundUser,UserResponse.class);
        return  ResponseEntity.status(200).body(response);

 }


// ! GET ALL BOOKS API END POINT
 @GetMapping("/all")
    public List<Users >findAllUsers(){

    return  userServiceImpl.findAllUsers();
 }

// !UPDATE USER RECORD

    @PutMapping("/update")

    public  ResponseEntity<?> updateUserDetails(@RequestBody Users updateUser){
try
{
Users updatedUser = userServiceImpl.updateUser(updateUser);
return ResponseEntity.status(HttpStatus.CREATED.value()).body(updatedUser);


    }catch (UserNotFoundException ex){
    return ResponseEntity.status(401).body(ex.getMessage());
}}


}
