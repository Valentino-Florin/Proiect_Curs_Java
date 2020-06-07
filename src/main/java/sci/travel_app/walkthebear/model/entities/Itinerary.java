package sci.travel_app.walkthebear.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ITINERARY")
public class Itinerary {
    @Id
    @Column(name = "ITINERARY_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long itineraryId;
    @Column(name = "NAME", nullable = false)
    private String name ;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_START")
    private Date startDate;

    public Itinerary(){}

    //remove this constructor after test
    public Itinerary(String name){
        this.name = name;
    }

    public Itinerary(String name, String description, AppUser user, Date startDate) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.created = new Date();
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerary itinerary = (Itinerary) o;
        return itineraryId == itinerary.itineraryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itineraryId);
    }
}
