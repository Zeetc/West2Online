package domain;

public class City {
    private int id;
    private int cid;
    private String name;
    private int confirmed;
    private int recovered;
    private int deaths;

    public City() {

    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", confirmed=" + confirmed +
                ", recovered=" + recovered +
                ", deaths=" + deaths +
                '}';
    }

    public City(int id, int cid, String name, int confirmed, int recovered, int deaths) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
