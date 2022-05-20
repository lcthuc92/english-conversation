package thucluong.english.conversation.api.services;

import static thucluong.english.conversation.common.Constant.HOST;
import static thucluong.english.framework.utils.helpers.RequestHelper.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;

import thucluong.english.conversation.utils.TokenUtils;
import thucluong.english.framework.api.dto.RequestDto;

public class VocabularyApiService {

  private static final String GET_ALL_URL = HOST + "/vocabulary/getAll";

  private final Context context;

  public VocabularyApiService(Context context) {
    this.context = context;
  }

  public void getByTopicId(long topicId, Response.Listener<String> responseListener,
    Response.ErrorListener errorListener) {
    HashMap<String, String> params = new HashMap<>();
    params.put("topicId", String.valueOf(topicId));
    RequestDto requestDto = RequestDto.builder()
      .setMethod(Request.Method.GET)
      .setUrl(GET_ALL_URL)
      .setParams(params)
      .build();
    if (TokenUtils.injectToken(requestDto.getHeaders(), errorListener)) {
      request(context, requestDto, responseListener, errorListener);
    }
  }
}
