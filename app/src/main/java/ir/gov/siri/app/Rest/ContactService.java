package ir.gov.siri.app.Rest;

import java.util.List;

import ir.gov.siri.app.Contact.Contact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactService {

    @GET("json/glide.json")
    Call<List<Contact>> getContacts();
}
