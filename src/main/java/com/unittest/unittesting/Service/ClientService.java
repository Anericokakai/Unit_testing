package com.unittest.unittesting.Service;

import com.unittest.unittesting.Repository.ClientRepository;
import com.unittest.unittesting.models.Client;
import com.unittest.unittesting.tdo.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientService {
@Autowired
    private  final ClientRepository clientRepository;
@Autowired
private  final ModelMapper modelMapper;

    public Client saveNewClient( ClientRequest clientRequest){
        Client newClient=modelMapper.map(clientRequest, Client.class);

        clientRepository.save(newClient);
        return newClient;
    }



    public List<Client> findAllClients (){
        return clientRepository.findAll();
    }


    public Client findByClientId( int id){

      return  clientRepository.findByid(id);
    }
}


