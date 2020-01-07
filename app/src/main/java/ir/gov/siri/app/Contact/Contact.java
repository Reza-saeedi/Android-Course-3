package ir.gov.siri.app.Contact;

public class Contact  {
    private long id;
    private String name;
    private String family;
    private String phone;

    public Contact(String name, String family, String phone) {
        this.name = name;
        this.family = family;
        this.phone = phone;
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
}
