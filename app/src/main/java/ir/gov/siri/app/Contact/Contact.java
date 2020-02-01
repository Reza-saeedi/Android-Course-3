package ir.gov.siri.app.Contact;

import com.google.gson.annotations.SerializedName;

public class Contact  {
    private long id;
    private String name;
    private String family;

    private ImageUrl url;



    @SerializedName("timestamp")
    private String phone;



    public Contact(String name, String family, String phone) {
        this.name = name;
        this.family = family;
        this.phone = phone;
    }

    public  Contact()
    {

    }


    public ImageUrl getUrl() {
        return url;
    }

    public void setUrl(ImageUrl url) {
        this.url = url;
    }

    public void setUrl(String url) {
        ImageUrl imageUrl=new ImageUrl();
        imageUrl.small=url;
        this.url = imageUrl;
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


    public class  ImageUrl{
        String small;
        String large;
        String names;

        public String getSmall() {
            return small;
        }
    }
}
