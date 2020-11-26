package org.example.domain;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ContactDaoDB {

    @PersistenceContext
    private EntityManager em;

    public void addContact(Contact c){
        em.persist(c);
    }

    public List<Contact> getAll(){
        TypedQuery<Contact> namedQ = em.createNamedQuery("Contact.findAll", Contact.class);
        return namedQ.getResultList();
    }

    public List<Contact> getByQuery(String q){
        return getAll().stream()
                .filter(c -> c.allDataText().contains(q))
                .collect(Collectors.toList());
    }

    public Contact getById(int id){
        return em.find(Contact.class, id);
    }

    public boolean deleteContact(int id){
        Contact c = getById(id);
        if (c == null) return false;
        em.remove(c);
        return true;
    }

    public void updateContact(Contact c){
        em.merge(c);
    }
}
