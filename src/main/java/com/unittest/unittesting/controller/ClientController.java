package com.unittest.unittesting.controller;

import com.unittest.unittesting.Service.ClientService;
import com.unittest.unittesting.models.Client;
import com.unittest.unittesting.tdo.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/clients")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private  ClientService clientService;
    @PostMapping("/signUp")
    public ResponseEntity<?> saveNewClient (@RequestBody ClientRequest clientRequest){

        Client savedClient= clientService.saveNewClient(clientRequest);
        return ResponseEntity.status(200).body(savedClient);

    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable("userId") int userId){

        return ResponseEntity.ok().body(clientService.findByClientId(userId));
    }

    @GetMapping("/all")
    public  ResponseEntity<?> findAllClients(){
        return ResponseEntity.ok().body(clientService.findAllClients());
    }
}
