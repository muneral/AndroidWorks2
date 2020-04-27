package chapter.android.aweme.ss.com.homework.Exercise3;

public class Item {
    private String title;
    private String description;
    private String time;
    private int imageId;

    public Item(String title, String description, String time,int imageId){
        this.description = description;
        this.title = title;
        this.time = time;
        this.imageId = imageId ;
    }

    public String getTitle() {
        return title;
    }

    public String getTTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }
}
