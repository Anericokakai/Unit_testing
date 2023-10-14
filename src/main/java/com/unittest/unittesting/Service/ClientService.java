package com.unittest.unittesting.Service;

import com.unittest.unittesting.Repository.ClientRepository;
import com.unittest.unittesting.models.Client;
import com.unittest.unittesting.tdo.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientService {

    private  final ClientRepository clientRepository;
private  final ModelMapper modelMapper;

    public Client saveNewClient( ClientRequest clientRequest){
        Client newClient=modelMapper.map(clientRequest, Client.class);

        return clientRepository.save(newClient);
    }



    public List<Client> findAllClients (){
        return clientRepository.findAll();
    }


    public Client findByClientId( int id){

      return  clientRepository.findByid(id);
    }
}


