package com.unittest.unittesting.Repository;

import com.unittest.unittesting.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<Users,Integer> {

Users findByEmail(String email);
Users findByid(int userId);
}
