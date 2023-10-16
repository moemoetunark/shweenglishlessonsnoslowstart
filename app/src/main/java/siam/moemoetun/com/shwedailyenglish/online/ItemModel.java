package siam.moemoetun.com.shwedailyenglish.online;
public class ItemModel {
    private String title;
    private String description;
    private String category;
    private String url;
    private String image;

    public ItemModel() {
        // Default constructor required for Firebase
    }

    public ItemModel(String title, String description, String category, String url, String image) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.url = url;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}

