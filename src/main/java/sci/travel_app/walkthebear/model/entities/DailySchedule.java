package sci.travel_app.walkthebear.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DailySchedule {
    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long dayId;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "ITINERARY_ID")
    private Itinerary itinerary;
    @Column(name = "NAME")
    private String name;
//    @ElementCollection
//    @MapKeyColumn(name = "TIME_SLOT")
//    @Column(name = "PLACE")
//    @CollectionTable(name = "DAY_MAPPING")
//    private Map<String, Place> day;
////    public Map<String, Place> day = new HashMap<>();

    public DailySchedule(){}

    public DailySchedule(String name) {
    }
    public DailySchedule(AppUser user, Itinerary itinerary, String name) {
        this.user = user;
        this.itinerary = itinerary;
        this.name = name;
    }

    public long getDayId() {
        return dayId;
    }
    public void setDayId(long dayId) {
        this.dayId = dayId;
    }
    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailySchedule that = (DailySchedule) o;
        return dayId == that.dayId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayId);
    }
}
