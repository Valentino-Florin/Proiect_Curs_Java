package sci.travel_app.walkthebear.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @Column(name = "RATING_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    @Column(name = "STAR_RATING", nullable = false)
    private int starRating;
    @Column(name = "COMMENT", nullable = false)
    private String comment;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "RATED_PLACE")
    private Place place;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date created;

    public Rating(){}
    public Rating(int starRating, String comment, AppUser user, Place place) {
        this.starRating = starRating;
        this.comment = comment;
        this.user = user;
        this.place = place;
        this.created = new Date();
    }

    public int getStarRating() {
        return starRating;
    }
    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
}
