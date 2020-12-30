package domain;

public class Country {
    private int id;
    private String name;
    private int sq_km_area;
    private String capital_city;
    private String continent;
    private String location;
    private String abbreviation;
    private int confirmed;
    private int recovered;
    private int deaths;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sq_km_area=" + sq_km_area +
                ", capital_city='" + capital_city + '\'' +
                ", continent='" + continent + '\'' +
                ", location='" + location + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", confirmed=" + confirmed +
                ", recovered=" + recovered +
                ", deaths=" + deaths +
                '}';
    }

    public Country(int id, String name, int sq_km_area, String capital_city, String continent, String location, String abbreviation, int confirmed, int recovered, int deaths) {
        this.id = id;
        this.name = name;
        this.sq_km_area = sq_km_area;
        this.capital_city = capital_city;
        this.continent = continent;
        this.location = location;
        this.abbreviation = abbreviation;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public Country() {

    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSq_km_area() {
        return sq_km_area;
    }

    public void setSq_km_area(int sq_km_area) {
        this.sq_km_area = sq_km_area;
    }

    public String getCapital_city() {
        return capital_city;
    }

    public void setCapital_city(String capital_city) {
        this.capital_city = capital_city;
    }

}
