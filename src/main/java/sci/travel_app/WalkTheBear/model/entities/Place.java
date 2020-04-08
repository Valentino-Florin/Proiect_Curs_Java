package sci.travel_app.WalkTheBear.model.entities;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import sci.travel_app.WalkTheBear.model.misc.Category;
import sci.travel_app.WalkTheBear.model.misc.SubCategory;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLACE")
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
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubCategory subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    //Place mainPic;
//    @Lob
//    @Type(type = "org.hibernate.type.BinaryType")
//    private byte[] thumbnail;
    //Place galleryPic;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "date_created")
    private Date created;
    @OneToOne
    @JoinColumn(name = "created_by")
    private AppUser user;

    public Place(){

    }


    public Place(String name, String county, String city, String address, String phoneNumber, String email, Category category, SubCategory subcategory, String description, Date created, AppUser user) {
        this.name = name;
        this.county = county;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.created = new Date();
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
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

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
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

    public void setName(String name) {
        this.name = name;
    }
}
