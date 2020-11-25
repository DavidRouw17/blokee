package org.example.domain;

import java.util.ArrayList;
import java.util.Arrays;


public class ContactDao {

    private ArrayList<Contact> contacts = new ArrayList<Contact>(Arrays.asList(
            Contact.builder().firstName("Henk").surname("Music").email("henk@gmail.com").id(0).build(),
            Contact.builder().firstName("Peter").surname("Muscles").email("peter@gmail.com").id(1).build(),
            Contact.builder().firstName("Aart").surname("Staartjes").email("sesamstraat@hotmail.com").id(2).build(),
            Contact.builder().firstName("David").surname("Rouw").email("david_rouw@gmail.com").id(3).build()
    ));

    private int id = 4;


    public ArrayList<Contact> getAll() {
        return contacts;
    }

    public ArrayList<Contact> getByQuery(String search) {
        ArrayList<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.allDataText().contains(search)) {
                result.add(contact);
            }
        }
        return result;
    }

    public Contact getById(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public void addContact(Contact c) {
        Contact added = c;
        added.setId(this.id);
        this.id++;
        contacts.add(added);
    }

    public boolean deleteContact(int id) {
        return contacts.remove(getById(id));
    }

    public void updateContact(int id, Contact c) {
        int loc = 0;
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                break;
            }
            loc++;
        }
        contacts.set(loc, c);
    }


}
