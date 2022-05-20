package thucluong.english.conversation.entities;

import com.google.gson.annotations.SerializedName;

import thucluong.english.framework.api.entity.BaseEntity;

public class VocabularyEntity extends BaseEntity {

	@SerializedName("word")
	private String word;

	@SerializedName("word_type")
	private String wordType;

	@SerializedName("pronunciation")
	private String pronunciation;

	@SerializedName("meaning")
	private String meaning;

	@SerializedName("topic_id")
	private Long topicId;

	@SerializedName("topic_name")
	private String topicName;

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getWordType() {
    return wordType;
  }

  public void setWordType(String wordType) {
    this.wordType = wordType;
  }

  public String getPronunciation() {
    return pronunciation;
  }

  public void setPronunciation(String pronunciation) {
    this.pronunciation = pronunciation;
  }

  public String getMeaning() {
    return meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }
}
