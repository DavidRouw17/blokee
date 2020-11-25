package org.example.resources;

import org.example.domain.Contact;
import org.example.domain.ContactDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/contacts")

@Produces(MediaType.APPLICATION_JSON)
public class ContactsResource {

    private static final ContactDao dao = new ContactDao();

    @GET
    public ArrayList<Contact> getResult(@QueryParam("q") String q) {
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
        dao.updateContact(id, c);
    }
}
