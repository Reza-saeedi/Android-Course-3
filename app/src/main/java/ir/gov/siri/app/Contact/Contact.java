package ir.gov.siri.app.Contact;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;


@Entity
public class Contact {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String family;

    private long imageId;

    @ToOne(joinProperty = "imageId")
    private ImageUrl url;


    @SerializedName("timestamp")
    private String phone;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2046468181)
    private transient ContactDao myDao;


    @Generated(hash = 1846866033)
    public Contact(Long id, String name, String family, long imageId,
            String phone) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.imageId = imageId;
        this.phone = phone;
    }


    @Generated(hash = 672515148)
    public Contact() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getFamily() {
        return this.family;
    }


    public void setFamily(String family) {
        this.family = family;
    }


    public long getImageId() {
        return this.imageId;
    }


    public void setImageId(long imageId) {
        this.imageId = imageId;
    }


    public String getPhone() {
        return this.phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Generated(hash = 343965754)
    private transient Long url__resolvedKey;



    public ImageUrl getImageUrl()
    {
        return url;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 897137481)
    public ImageUrl getUrl() {
        long __key = this.imageId;
        if (url__resolvedKey == null || !url__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ImageUrlDao targetDao = daoSession.getImageUrlDao();
            ImageUrl urlNew = targetDao.load(__key);
            synchronized (this) {
                url = urlNew;
                url__resolvedKey = __key;
            }
        }
        return url;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2112431905)
    public void setUrl(@NotNull ImageUrl url) {
        if (url == null) {
            throw new DaoException(
                    "To-one property 'imageId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.url = url;
            imageId = url.getId();
            url__resolvedKey = imageId;
        }
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2088270543)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getContactDao() : null;
    }
}