package ir.gov.siri.app.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {


    public List<Contact> getContacts()
    {
      List<Contact> contacts=new ArrayList<>();
      contacts.add(new Contact("ali","aliF","09122222211"));
      contacts.add(new Contact("reza","rezaF","09122222233"));
      contacts.add(new Contact("hasan","hasani","09122222244"));
      contacts.add(new Contact("amir","amiri","09122222255"));

        contacts.add(new Contact("ali","aliF","09122222211"));
        contacts.add(new Contact("reza","rezaF","09122222233"));
        contacts.add(new Contact("hasan","hasani","09122222244"));
        contacts.add(new Contact("amir","amiri","09122222255"));
      return contacts;
    }


    public List<Contact> getContacts(String search)
    {
        List<Contact> contacts=new ArrayList<>();
        if(search.length()%2==0) {
            contacts.add(new Contact("ali", "aliF", "09122222211"));
            contacts.add(new Contact("reza", "rezaF", "09122222233"));
            contacts.add(new Contact("hasan", "hasani", "09122222244"));
            contacts.add(new Contact("amir", "amiri", "09122222255"));
        }

        contacts.add(new Contact("ali","aliF","09122222211"));
        contacts.add(new Contact("reza","rezaF","09122222233"));
        contacts.add(new Contact("hasan","hasani","09122222244"));
        contacts.add(new Contact("amir","amiri","09122222255"));
        return contacts;
    }


}
