package sci.travel_app.WalkTheBear.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ITINERARY")
public class Itinerary {
    @Id
    @Column(name = "ITINERARY_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    @Column(name = "NAME", nullable = false)
    private String name ;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date created;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_START")
    private Date startDate;
//    public  List<Place> unplannedPlaces = new ArrayList<>();
//    public  List<DailySchedule> Schedule = new ArrayList<>();

    public Itinerary(){}

    //remove this constructor after test
    public Itinerary(String name){
        this.name = name;
    }

    public Itinerary(String name, String description, AppUser user, Date startDate, Date endDate) {
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




}
