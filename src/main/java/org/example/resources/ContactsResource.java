package org.example.resources;

import org.example.domain.Contact;
import org.example.domain.ContactDao;
import org.example.domain.ContactDaoDB;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/contacts")

@Produces(MediaType.APPLICATION_JSON)
public class ContactsResource {

    @Inject
    private ContactDaoDB dao;

    @GET
    public List<Contact> getResult(@QueryParam("q") String q) {
        if (q != null){
            return dao.getByQuery(q.toLowerCase());
        }
        return dao.getAll();
    }


    @GET
    @Path("{id}")
    public Contact getById(@PathParam("id") int id) {
        return dao.getById(id);
    }

    @POST
    public Contact postContact(Contact contact) {
        dao.addContact(contact);
        return contact;
    }

    @DELETE
    @Path("{id}")
    public void deleteContact(@PathParam("id") int id) {
        if (!dao.deleteContact(id)){
            throw new BadRequestException("Contact does not exist!");
        }
    }

    @PUT
    @Path("{id}")
    public void updateContact(@PathParam("id") int id, Contact c) {
        dao.updateContact(c);
    }
}
