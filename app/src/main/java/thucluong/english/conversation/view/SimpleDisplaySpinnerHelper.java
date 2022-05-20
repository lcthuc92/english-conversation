package thucluong.english.conversation.view;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import thucluong.english.conversation.entities.interfaces.Displayable;

public class SimpleDisplaySpinnerHelper<T extends Displayable> {
  private final Context context;
  private final Spinner spinner;

  public SimpleDisplaySpinnerHelper(Context context, Spinner spinner) {
    this.spinner = spinner;
    this.context = context;
  }

  public void setItems(List<T> items) {
    ArrayAdapter<T> adapter = new SimpleDisplayArrayAdapter<T>(this.context, items);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.spinner.setAdapter(adapter);
  }

  public T getSelectedItem() {
    return (T) this.spinner.getSelectedItem();
  }
}
