package thucluong.english.conversation;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import thucluong.english.conversation.api.services.FolderApiService;
import thucluong.english.conversation.api.services.TopicApiService;
import thucluong.english.conversation.api.services.VocabularyApiService;
import thucluong.english.conversation.entities.FolderEntity;
import thucluong.english.conversation.entities.TopicEntity;
import thucluong.english.conversation.entities.VocabularyEntity;
import thucluong.english.conversation.view.SimpleDisplayDto;
import thucluong.english.conversation.view.SimpleDisplaySpinnerHelper;
import thucluong.english.framework.activity.BaseActivity;
import thucluong.english.framework.utils.JsonConvertUtils;

public class SlideActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

  private Spinner spFolder;
  private Spinner spTopic;
  private Spinner spTimeSlide;

  private SimpleDisplaySpinnerHelper<FolderEntity> spFolderHelper;
  private SimpleDisplaySpinnerHelper<TopicEntity> spTopicHelper;
  private SimpleDisplaySpinnerHelper<SimpleDisplayDto> spTimeSlideHelper;

  private TextView txtConversation;
  private TextView txtMeaning;
  private Button btnPlay;

  private final FolderApiService folderApiService;
  private final TopicApiService topicApiService;
  private final VocabularyApiService vocabularyApiService;

  private final List<VocabularyEntity> vocabularyEntities;
  private Handler handler = new Handler();
  private long mStartTime = 0;

  public SlideActivity() {
    this.folderApiService = new FolderApiService(this);
    this.topicApiService = new TopicApiService(this);
    this.vocabularyApiService = new VocabularyApiService(this);
    this.vocabularyEntities = new ArrayList<>();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_slide);

    this.spFolder = findViewById(R.id.spinner_folder);
    this.spFolder.setOnItemSelectedListener(this);
    this.spFolderHelper = new SimpleDisplaySpinnerHelper<>(this, spFolder);

    this.spTopic = findViewById(R.id.spinner_topic);
    this.spTopic.setOnItemSelectedListener(this);
    this.spTopicHelper = new SimpleDisplaySpinnerHelper<>(this, spTopic);

    this.spTimeSlide = findViewById(R.id.spinner_time);
    this.spTimeSlide.setOnItemSelectedListener(this);
    this.spTimeSlideHelper = new SimpleDisplaySpinnerHelper<>(this, spTimeSlide);
    this.spTimeSlideHelper.setItems(Arrays.stream(getResources().getStringArray(R.array.time_slide)).map(
      SimpleDisplayDto::new).collect(
      Collectors.toList()));

    this.txtConversation = findViewById(R.id.txtConversation);
    this.txtMeaning = findViewById(R.id.txtMeaning);
    this.btnPlay = findViewById(R.id.btnPlay);
    this.btnPlay.setOnClickListener(this);

    folderApiService.getAll(response -> {
      List<FolderEntity> folderEntities = JsonConvertUtils.parseJSONToObjects(response, FolderEntity.class);
      this.spFolderHelper.setItems(folderEntities);
    }, this);
  }

  private Runnable updateTimerThread = new Runnable() {
    public void run() {
      List<VocabularyEntity> entities = new ArrayList<>(vocabularyEntities);
      if (entities.size() == 0) {
        return;
      }

      final long start = mStartTime;
      final long handleTime = System.currentTimeMillis();
      long millis = handleTime - start;
      int seconds = (int) (millis / 1000);

      int intervalSecond = Integer.parseInt(spTimeSlideHelper.getSelectedItem().getDisplayText());
      int index = (seconds / intervalSecond) % entities.size();
      VocabularyEntity entity = entities.get(index);
      txtConversation.setText(entity.getWord());
      txtMeaning.setText(entity.getMeaning());

      handler.postDelayed(this, intervalSecond * 1000L);
    }
  };

  @Override
  public void onClick(View view) {
    if (view.getId() == btnPlay.getId()) {
      stopSlide();
      mStartTime = System.currentTimeMillis();
      handler.postDelayed(updateTimerThread, 1000);
    }
  }

  private void stopSlide() {
    txtMeaning.setText("");
    txtConversation.setText("");
    handler.removeCallbacks(updateTimerThread);
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    if (parent.getId() == spFolder.getId()) {
      stopSlide();
      FolderEntity item = (FolderEntity) spFolder.getAdapter().getItem(pos);
      topicApiService.getByFolderId(item.getId(), response -> {
        List<TopicEntity> topicEntities = JsonConvertUtils.parseJSONToObjects(response, TopicEntity.class);
        this.spTopicHelper.setItems(topicEntities);
      }, this);
    } else if (parent.getId() == spTopic.getId()) {
      stopSlide();
      TopicEntity item = (TopicEntity) spTopic.getAdapter().getItem(pos);
      vocabularyApiService.getByTopicId(item.getId(), response -> {
        List<VocabularyEntity> vocabularies = JsonConvertUtils.parseJSONToObjects(response, VocabularyEntity.class);
        vocabularyEntities.clear();
        vocabularyEntities.addAll(vocabularies);
      }, this);
    } else if (parent.getId() == spTimeSlide.getId()) {
      stopSlide();
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}