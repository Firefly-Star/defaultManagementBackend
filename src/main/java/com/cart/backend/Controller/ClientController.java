package com.cart.backend.Controller;

import com.cart.backend.DAO.ClientMapper;
import com.cart.backend.Entity.Client;
import com.cart.backend.Entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientMapper clientMapper;


    @RequestMapping("/api/user/client")
    public Result getClient(@RequestBody Client client) {

        List<Client> clients = clientMapper.getClients(client);
        return Result.Success(clients);

    }

}
