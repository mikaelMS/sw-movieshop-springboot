package com.oth.sw.mikesmovieshop.mikesmovieshop.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Movie {
    enum Media {
        DVD,
        BLURAY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_Id", updatable = false, nullable = false)
    private long id;

    @Size(max = 50)
    private String name;

    @Size(max = 20)
    private String director;

    // TOOD: date
//    @Temporal(TemporalType.DATE)
//    private Date releaseYear;
    @Column(name = "image_path")
    private String coverImagePath;
    private Double price;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private Media media;

    @Column(name = "available_status")
    private Boolean availableStatus;

    //    private ArrayList<Review> reviews;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Boolean getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(Boolean availableStatus) {
        this.availableStatus = availableStatus;
    }
}
