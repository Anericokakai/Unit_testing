package com.unittest.unittesting.Services;

import com.unittest.unittesting.Exceptions.UserExistException;
import com.unittest.unittesting.Exceptions.UserNotFoundException;
import com.unittest.unittesting.Repository.UserRepository;
import com.unittest.unittesting.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl {


@Autowired
  private  final  UserRepository userRepository;



    public Users saveUser(Users users ) throws UserExistException {

        if(userRepository.findByEmail(users.getEmail())!=null){
           throw new  UserExistException("user already exist");
        }

     Users savedUser=   userRepository.save(users);
var responseUser= Users.builder()
        .name(savedUser.getName())
        .role(savedUser.getRole())
        .id(savedUser.getId())
        .password(null)
        .email(savedUser.getEmail())
        .build();
        return responseUser;

    }


    public  Users findUserByEmail(String email) {

            return userRepository.findByEmail(email);


    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users findUserById(int userId)  throws Exception{
        if(userRepository.findByid(userId)==null){
            throw  new UserNotFoundException("user not found with the given id:"+userId);

        }

        return userRepository.findByid(userId);



    }


    public Users updateUser(Users user) throws UserNotFoundException {

        Optional<Users>  userExist=userRepository.findById(user.getId());
        if(userExist.isEmpty()){
            throw  new UserNotFoundException("user not found !");
            }

        Users ExistingUserRecord= userExist.get();

        ExistingUserRecord.setName(user.getName());
        ExistingUserRecord.setEmail(user.getEmail());
        ExistingUserRecord.setPassword(user.getPassword());

        return  ExistingUserRecord;
    }



}
