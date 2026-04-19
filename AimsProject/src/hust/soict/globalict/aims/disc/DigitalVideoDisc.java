package hust.soict.globalict.aims.disc;

public class DigitalVideoDisc {
    private static int nbDigitalVideoDiscs = 0;
    private int id;
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    // Constructors
    public DigitalVideoDisc() {
        super();
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title) {
        super();
        this.title = title;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super();
        this.category = category;
        this.title = title;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super();
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public float getCost() { return cost; }
    public void setCost(float cost) { this.cost = cost; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // Utils
    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public String toString() {
        return "DVD - [" + title + "] - [" + category + "] - [" + director + "] - [" + length + "]: [" + cost + "] $";
    }
}