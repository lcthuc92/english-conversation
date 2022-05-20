package thucluong.english.conversation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Optional;

import thucluong.english.conversation.R;
import thucluong.english.conversation.entities.interfaces.Displayable;

public class SimpleDisplayArrayAdapter<T extends Displayable> extends ArrayAdapter<T> {
  private final List<T> entities;
  private final Context mContext;

  public SimpleDisplayArrayAdapter(@NonNull Context context, List<T> entities) {
    super(context, R.layout.dropdown_item, entities);
    this.entities = entities;
    this.mContext = context;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    @SuppressLint("ViewHolder")
    View listItem = Optional.ofNullable(convertView)
      .orElseGet(() -> LayoutInflater.from(this.mContext).inflate(R.layout.dropdown_item, parent, false));
    T currentItem = getEntities().get(position);
    TextView textView = listItem.findViewById(R.id.dropdown_item_text);
    textView.setText(currentItem.getDisplayText());
    return listItem;
  }

  @Override
  public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View listItem = Optional.ofNullable(convertView)
      .orElseGet(() -> LayoutInflater.from(this.mContext).inflate(R.layout.dropdown_item, parent, false));
    T currentItem = getEntities().get(position);
    TextView textView = listItem.findViewById(R.id.dropdown_item_text);
    textView.setText(currentItem.getDisplayText());

    return listItem;
  }

  public List<T> getEntities() {
    return entities;
  }
}
