package thucluong.english.conversation;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import thucluong.english.framework.activity.BaseActivity;

public class SlideActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

  private Spinner spFolder;
  private Spinner spTopic;
  private Spinner spTimeSlide;

  private TextView txtConversation;
  private TextView txtMeaning;

  private ArrayAdapter<String> spFolderAdapter;
  private ArrayAdapter<String> spTopicAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_slide);

    ArrayList<String> sampleData = new ArrayList<>();
    sampleData.add("item 1");
    sampleData.add("item 2");
    sampleData.add("item 3");

    this.spFolder = findViewById(R.id.spinner_folder);
    this.spFolder.setOnItemSelectedListener(this);
    this.spFolderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sampleData);
    this.spFolderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.spFolder.setAdapter(this.spFolderAdapter);

    this.spTopic = findViewById(R.id.spinner_topic);
    this.spTopic.setOnItemSelectedListener(this);
    this.spTopicAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sampleData);
    this.spTopicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.spTopic.setAdapter(this.spFolderAdapter);

    this.spTimeSlide = findViewById(R.id.spinner_time);
    this.spTimeSlide.setOnItemSelectedListener(this);
    ArrayAdapter<CharSequence> timeSlideAdapter = ArrayAdapter.createFromResource(this, R.array.time_slide,
      android.R.layout.simple_spinner_item);
    timeSlideAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spTimeSlide.setAdapter(timeSlideAdapter);

    this.txtConversation = findViewById(R.id.txtConversation);
    this.txtMeaning = findViewById(R.id.txtMeaning);
  }

  private void bindSpinnerAdapter(ArrayAdapter<String> adapter, List<String> items) {
    adapter.clear();
    adapter.addAll(items);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onClick(View view) {

  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}