package google.login.antonio.fcmtestapp;

/**
 * Created by Antonio on 08/01/2018.
 */

public class Data {
    private String title;
    private String description;
    private String image;
    private String url;

    public Data(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getImage(){
        return this.image;
    }
    public String getUrl(){
        return this.url;
    }
    @Override
    public String toString() {
        return title + " - " + description;
    }
}
