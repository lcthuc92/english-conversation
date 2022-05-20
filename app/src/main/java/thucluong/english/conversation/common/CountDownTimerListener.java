package thucluong.english.conversation.common;

public interface CountDownTimerListener {
  void onTick(long millisUntilFinished);

  void onFinish();
}
