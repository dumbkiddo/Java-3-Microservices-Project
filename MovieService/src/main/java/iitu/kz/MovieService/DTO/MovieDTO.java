package iitu.kz.MovieService.DTO;

import javax.validation.constraints.NotNull;

public class MovieDTO {

    private Integer movieId;

    @NotNull(message="Title can't be empty")
    private String title;

    @NotNull(message="Small Description can't be empty")
    private String smallDesc;

    @NotNull(message="Long Description can't be empty")
    private String longDesc;

    @NotNull(message="Genre can't be empty")
    private Integer genre;

    @NotNull(message="Director can't be empty")
    private Integer directorId;

    @NotNull(message="Price can't be empty")
    private Double price;

    private String port;

    public Integer getMovieId() {
        return movieId;
    }
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSmallDesc() {
        return smallDesc;
    }
    public void setSmallDesc(String smallDesc) {
        this.smallDesc = smallDesc;
    }
    public String getLongDesc() {
        return longDesc;
    }
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
    public Integer getGenre() {
        return genre;
    }
    public void setGenre(Integer genre) {
        this.genre = genre;
    }
    public Integer getDirectorId() {
        return directorId;
    }
    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
}
