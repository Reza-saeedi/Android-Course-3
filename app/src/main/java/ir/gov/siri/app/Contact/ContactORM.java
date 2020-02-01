package ir.gov.siri.app.Contact;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ContactORM {
    @Id
    private long id;
    private String name;
    private String family;

    private String url;



    @SerializedName("timestamp")
    private String phone;




    @Generated(hash = 1057605855)
    public ContactORM(long id, String name, String family, String url,
            String phone) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.url = url;
        this.phone = phone;
    }

    @Generated(hash = 1308284861)
    public ContactORM() {
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

