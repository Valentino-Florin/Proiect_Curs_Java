<<<<<<< Updated upstream
<<<<<<< Updated upstream
package sci.travel_app.WalkTheBear.model.entities;

import javax.persistence.*;
import java.util.Date;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "PLACE")
@DiscriminatorColumn(name = "CATEGORY")
public class Place {
    @Id
    @Column(name = "PLACE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "COUNTY", nullable = false)
    private String county;
    @Column(name = "CITY", nullable = false)
    private String city;
    @Column(name = "ADDRESS", nullable = false)
    private String address;
    @Column(name = "COORDINATES")
    private String coordinates;
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    //Place mainPic;
    //Place galleryPic;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date created;
    @OneToOne
    @JoinColumn(name = "created_by")
    private AppUser user;

    public Place(){

    }

    public Place(String name, String city, String county, String address, String phoneNumber, String email, String description,AppUser user ) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.created = new Date();
        this.user = getUser();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }
}
=======
=======
>>>>>>> Stashed changes
package sci.travel_app.WalkTheBear.model.entities;

import javax.persistence.*;
import java.util.Date;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "PLACE")
@DiscriminatorColumn(name = "CATEGORY")
public class Place {
    @Id
    @Column(name = "PLACE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "COUNTY", nullable = false)
    private String county;
    @Column(name = "CITY", nullable = false)
    private String city;
    @Column(name = "ADDRESS", nullable = false)
    private String address;
    @Column(name = "COORDINATES")
    private String coordinates;
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    //Place mainPic;
    //Place galleryPic;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date created;
    @OneToOne
    @JoinColumn(name = "created_by")
    private AppUser user;

    public Place(){

    }

    public Place(String name, String city, String county, String address, String phoneNumber, String email, String description,AppUser user ) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.created = new Date();
        this.user = getUser();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }
}
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
