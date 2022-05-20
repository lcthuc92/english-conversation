package thucluong.english.conversation.api.services;

import static thucluong.english.conversation.common.Constant.HOST;
import static thucluong.english.framework.utils.helpers.RequestHelper.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;

import thucluong.english.conversation.utils.TokenUtils;
import thucluong.english.framework.api.dto.RequestDto;

public class TopicApiService {

  private static final String GET_ALL_URL = HOST + "/topic/getAll";

  private final Context context;

  public TopicApiService(Context context) {
    this.context = context;
  }

  public void getByFolderId(long folderId, Response.Listener<String> responseListener,
    Response.ErrorListener errorListener) {
    HashMap<String, String> params = new HashMap<>();
    params.put("folderId", String.valueOf(folderId));
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
