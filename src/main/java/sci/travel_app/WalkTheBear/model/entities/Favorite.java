package sci.travel_app.WalkTheBear.model.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "FAVORITE")
public class Favorite {
    @Id
    @Column(name = "FAV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @OneToOne
    @JoinColumn(name = "FK_USER_ID")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "FK_PLACE_ID")
    private Place place;
    @Column(name = "DATE_ADDED")
    private Date dateAdded;
    public Favorite(){}
    public Favorite(AppUser user, Place favPlace) {
        this.user = user;
        this.place = favPlace;
        this.dateAdded = new Date();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "user=" + user +
                ", favPlace=" + place +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
