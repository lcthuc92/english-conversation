package thucluong.english.conversation.entities;

import com.google.gson.annotations.SerializedName;

import thucluong.english.conversation.entities.interfaces.Displayable;
import thucluong.english.framework.api.entity.BaseEntity;

public class FolderEntity extends BaseEntity implements Displayable {

  @SerializedName("name")
  private String name;

  @SerializedName("description")
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getDisplayText() {
    return getName();
  }
}
