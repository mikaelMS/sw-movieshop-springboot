package com.oth.sw.mikesmovieshop.mikesmovieshop.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

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

    @Size(max = 4)
    private String year;

    @Column(name = "image_path")
    private String coverImagePath;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition="LONGTEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private Media media;

    @Column(name = "on_stock", nullable = false)
    @Min(value = 0, message = "*Number on stock has to be non negative number")
    private Double onStock;

    @Column(name = "available_status", nullable = false)
    private Boolean availableStatus;

    private Double rating;

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
        return this.price;
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

    public void setAvailableStatus() {
        this.availableStatus = this.onStock > 0;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getOnStock() {
        return onStock;
    }

    public void setOnStock(Double onStock) {
        this.onStock = onStock;
    }

    public void setAvailableStatus(Boolean availableStatus) {
        this.availableStatus = availableStatus;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", year='" + year + '\'' +
                ", coverImagePath='" + coverImagePath + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", media=" + media +
                ", onStock=" + onStock +
                ", availableStatus=" + availableStatus +
                '}';
    }
}
