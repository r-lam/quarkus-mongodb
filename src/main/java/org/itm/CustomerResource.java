package org.itm;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

@Path("/customers")
@Consumes("application/json")
@Produces("application/json")
public class CustomerResource {

    @GET
    public List<Customer> list() {
        return Customer.listAll();
    }

    @GET
    @Path("/{id}")
    public Customer get(@PathParam("id") String id) {
        return Customer.findById(new ObjectId(id));
    }

    @POST
    public Response create(Customer customer) {
        customer.persist();
        return Response.status(201).build();
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Customer customer) {
        customer.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Customer customer = Customer.findById(new ObjectId(id));
        customer.delete();
    }

    @GET
    @Path("/search/{name}")
    public Customer search(@PathParam("name") String name) {
        return Customer.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Customer.count();
    }
}