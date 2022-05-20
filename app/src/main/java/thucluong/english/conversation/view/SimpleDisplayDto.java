package thucluong.english.conversation.view;

import thucluong.english.conversation.entities.interfaces.Displayable;

public class SimpleDisplayDto implements Displayable {

  private String value;

  public SimpleDisplayDto(String value) {
    this.value = value;
  }

  public String getDisplayText() {
    return this.value;
  }
}
