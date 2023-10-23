package com.unittest.unittesting.Service;

import com.unittest.unittesting.Exceptions.UserExistException;
import com.unittest.unittesting.Exceptions.UserNotFoundException;
import com.unittest.unittesting.Repository.ClientRepository;
import com.unittest.unittesting.models.Client;
import com.unittest.unittesting.tdo.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientService {
@Autowired
    private  final ClientRepository clientRepository;
@Autowired
private  final ModelMapper modelMapper;

    public Client saveNewClient( ClientRequest clientRequest) throws UserExistException {
        Client newClient=modelMapper.map(clientRequest, Client.class);

        if(clientRepository.findByemail(clientRequest.getEmail())!=null){
            throw  new UserExistException("user already exist");
        }
        clientRepository.save(newClient);
        return newClient;
    }



    public List<Client> findAllClients (){
        return clientRepository.findAll();
    }


    public Client findByClientId( int id) throws UserNotFoundException {

      Client client  =clientRepository.findByid(id);

      if(client!=null){
          return client;
      }else {
          throw  new UserNotFoundException("User not found with the id :"+id);
      }
    }
}


