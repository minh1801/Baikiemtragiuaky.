package vn.edu.bit240157.foodmanager.model;

/** Nguyễn Văn Minh - BIT240157. One category has many dishes. */
public class DishCategory {
  public String id, name, description;

  public DishCategory(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  @Override
  public String toString() {
    return name;
  }
}
