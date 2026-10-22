package vn.edu.bit240157.foodmanager.model;

public class DishImage {
  public String imageUrl;
  public boolean isThumbnail;

  public DishImage(String url, boolean thumbnail) {
    imageUrl = url;
    isThumbnail = thumbnail;
  }
}
