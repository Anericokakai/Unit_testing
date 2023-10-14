package com.unittest.unittesting.Repository;

import com.unittest.unittesting.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByid(int id);
    Client findByemail(String email);
}
