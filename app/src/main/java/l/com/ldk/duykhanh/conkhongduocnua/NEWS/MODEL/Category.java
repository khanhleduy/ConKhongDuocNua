package l.com.ldk.duykhanh.conkhongduocnua.NEWS.MODEL;

public class Category {
    private int urlImage;
    private String title;

    public Category(int urlImage, String title) {
        this.urlImage = urlImage;
        this.title = title;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

