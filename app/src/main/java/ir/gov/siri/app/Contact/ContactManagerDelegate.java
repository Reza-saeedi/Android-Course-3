package ir.gov.siri.app.Contact;

import java.util.List;

public interface ContactManagerDelegate {

    void onDataLoaded(List<Contact> contacts);
}
