package org.acme.controller;


import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.dto.CustomerDTO;
import org.acme.service.CustomerService;

@Path("/api/customers")
public class CustomerController {

  @Inject
  private CustomerService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
   public List<CustomerDTO> findAll (){
     return service.findAll();
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public CustomerDTO findCustumerById(@PathParam("id") Long id){
    return service.findCustomerById(id);
   }

  @POST
  @Transactional
  public Response save(CustomerDTO dto){
    try{
      service.create(dto);
      return Response.ok().build();
    } catch (Exception e){
        e.printStackTrace();
        return Response.serverError().build();
    }
  }

  @PUT
  @Path("/id")
  @Transactional
  public Response update(@PathParam("id") Long id, CustomerDTO dto){

    try{
      service.update(id, dto);
      return Response.accepted().build();
    }catch (Exception e ){
      e.printStackTrace();
      return Response.serverError().build();
    }
  }

  @DELETE
  @Path("/id")
  @Transactional
  public Response delete (@PathParam("id") Long id){

    try{
      service.delete(id);
      return Response.accepted().build();
    }catch (Exception e){
        e.printStackTrace();
        return Response.serverError().build();
    }
  }
}
