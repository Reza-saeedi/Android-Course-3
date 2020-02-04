package ir.gov.siri.app.Contact;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class  ImageUrl {
    @Id(autoincrement = true)
    private Long id;
    String small;
    String large;
    String names;
    @Generated(hash = 42544871)
    public ImageUrl(Long id, String small, String large, String names) {
        this.id = id;
        this.small = small;
        this.large = large;
        this.names = names;
    }
    @Generated(hash = 792005330)
    public ImageUrl() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSmall() {
        return this.small;
    }
    public void setSmall(String small) {
        this.small = small;
    }
    public String getLarge() {
        return this.large;
    }
    public void setLarge(String large) {
        this.large = large;
    }
    public String getNames() {
        return this.names;
    }
    public void setNames(String names) {
        this.names = names;
    }
}