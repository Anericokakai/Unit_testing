package com.unittest.unittesting.Repository;

import com.unittest.unittesting.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository   extends JpaRepository<Long, Users> {

    Optional<Users> findUserByEmail(String email);
}
