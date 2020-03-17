package sci.travel_app.WalkTheBear.model.entities;

import sci.travel_app.WalkTheBear.model.misc.AppUserRole;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
public class AppUser {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    @Column(name = "USER_NAME", length=50, nullable = false)
    private String userName;
    @Column(name = "PASSWORD", length=50, nullable = false)
    private String password;
    @Column(name = "EMAIL", length=200, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false )
    private AppUserRole role;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date created;
//    public List<Place> favoritePlaces = new ArrayList<>();


    public AppUser() {
    }
    public AppUser(String userName, String password, String email, AppUserRole role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.created = new Date();
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public AppUserRole getRole() {
        return role;
    }
    public void setRole(AppUserRole role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }


}
