package com.cart.backend.DAO;

import com.cart.backend.Entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    public List<Client> getClients(Client client);

}
