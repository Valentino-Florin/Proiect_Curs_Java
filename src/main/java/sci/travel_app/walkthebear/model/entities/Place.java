package sci.travel_app.walkthebear.model.entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.model.misc.SubCategory;

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
    @Column(name = "DESCRIPTION", length=1000, nullable = false)
    private String description;

    @Column(name = "THUMBNAIL", nullable = true)
    private String thumbnailFileName;
    @Column (name = "GALLERY_IMAGE1", nullable = true)
    private String galleryImage1FileName;
    @Column (name = "GALLERY_IMAGE2", nullable = true)
    private String galleryImage2FileName;
    @Column (name = "GALLERY_IMAGE3", nullable = true)
    private String galleryImage3FileName;
    @Column (name = "GALLERY_IMAGE4", nullable = true)
    private String galleryImage4FileName;
    @Column (name = "GALLERY_IMAGE5", nullable = true)
    private String galleryImage5FileName;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "date_created")
    private Date created;
    @OneToOne
    @JoinColumn(name = "created_by")
    private AppUser user;

    @Transient
    private MultipartFile thumbnail;

    //constructors
    public Place(){
    }

    public Place(String name, String county, String city, String address, String phoneNumber, String email, Category category, SubCategory subcategory, String description, String galleryImage1FileName, String galleryImage2FileName, String galleryImage3FileName, String galleryImage4FileName, String galleryImage5FileName, AppUser user) {
        this.name = name;
        this.county = county;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.galleryImage1FileName = galleryImage1FileName;
        this.galleryImage2FileName = galleryImage2FileName;
        this.galleryImage3FileName = galleryImage3FileName;
        this.galleryImage4FileName = galleryImage4FileName;
        this.galleryImage5FileName = galleryImage5FileName;
        this.created = new Date();
        this.user = user;
    }

    //getters & setters
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubcategory() {
        return subcategory;
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

    public String getThumbnailFileName() {
        return thumbnailFileName;
    }

    public void setThumbnailFileName(String thumbnailFileName) {
        this.thumbnailFileName = thumbnailFileName;
    }

    public String getGalleryImage1FileName() {
        return galleryImage1FileName;
    }

    public void setGalleryImage1FileName(String galleryImage1FileName) {
        this.galleryImage1FileName = galleryImage1FileName;
    }

    public String getGalleryImage2FileName() {
        return galleryImage2FileName;
    }

    public void setGalleryImage2FileName(String galleryImage2FileName) {
        this.galleryImage2FileName = galleryImage2FileName;
    }

    public String getGalleryImage3FileName() {
        return galleryImage3FileName;
    }

    public void setGalleryImage3FileName(String galleryImage3FileName) {
        this.galleryImage3FileName = galleryImage3FileName;
    }

    public String getGalleryImage4FileName() {
        return galleryImage4FileName;
    }

    public void setGalleryImage4FileName(String galleryImage4FileName) {
        this.galleryImage4FileName = galleryImage4FileName;
    }

    public String getGalleryImage5FileName() {
        return galleryImage5FileName;
    }

    public void setGalleryImage5FileName(String galleryImage5FileName) {
        this.galleryImage5FileName = galleryImage5FileName;
    }


    //getters & setters for transient field
    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }


// methods

    @Override
    public String toString() {
        return name + ", " + county  + ", " + city  ;
    }

    @Transient
    public String getThumbnailImagePath(){
        if (thumbnailFileName == null || id == 0) return null;
        return "/user-images/" + id + "/" + thumbnailFileName;
    }
    @Transient
    public String getGalleryImage1Path(){
        if (galleryImage1FileName == null || id == 0) return null;
        return "/user-images/" + id + "/" + galleryImage1FileName;
    }
    @Transient
    public String getGalleryImage2Path(){
        if (galleryImage2FileName == null || id == 0) return null;
        return "/user-images/" + id + "/" + galleryImage2FileName;
    }
    @Transient
    public String getGalleryImage3Path(){
        if (galleryImage3FileName == null || id == 0) return null;
        return "/user-images/" + id + "/" + galleryImage3FileName;
    }
    @Transient
    public String getGalleryImage4Path(){
        if (galleryImage4FileName == null || id == 0) return null;
        return "/user-images/" + id + "/" + galleryImage4FileName;
    }
    @Transient
    public String getGalleryImage5Path(){
        if (galleryImage5FileName == null || id == 0) return null;
        return "/user-images/" + id + "/" + galleryImage5FileName;
    }

}
