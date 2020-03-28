package com.oth.sw.mikesmovieshop.mikesmovieshop.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Movie {
    enum Media {
        DVD,
        BLURAY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_Id", updatable = false, nullable = false)
    private long movieId;

    @Column(nullable = false)
    @Size(max = 50)
    private String name;

    @Size(max = 20)
    private String director;

    // TOOD: date
//    @Temporal(TemporalType.DATE)
//    private Date releaseYear;
    @Column(name = "image_path")
    private String coverImagePath;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition="LONGTEXT")
    private String description;

    // TODO: can be both -> array
    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private Media media;

    @Column(name = "available_status", nullable = false)
    private Boolean availableStatus;

    //    private ArrayList<Review> reviews;

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", coverImagePath='" + coverImagePath + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", media=" + media +
                ", availableStatus=" + availableStatus +
                '}';
    }
}
