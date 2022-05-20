package thucluong.english.conversation.api.services;

import static thucluong.english.conversation.common.Constant.HOST;
import static thucluong.english.conversation.common.Constant.USER_NAME;
import static thucluong.english.framework.utils.helpers.RequestHelper.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thucluong.english.conversation.database.DbHelper;
import thucluong.english.conversation.entities.FolderEntity;
import thucluong.english.conversation.utils.TokenUtils;
import thucluong.english.framework.api.dto.RequestDto;
import thucluong.english.framework.database.model.AppSettings;

public class FolderApiService {

  private static final String GET_ALL_URL = HOST + "/folder/getAll";

  private final Context context;

  public FolderApiService(Context context) {
    this.context = context;
  }

  public void getAll(Response.Listener<String> responseListener,
    Response.ErrorListener errorListener) {
    Map<String, String> params = new HashMap<>();
    AppSettings settings = DbHelper.getInstance().getAppSettingsDao().getByName(USER_NAME);
    if (settings != null) {
      params.put("username", settings.getValue());
    }
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
