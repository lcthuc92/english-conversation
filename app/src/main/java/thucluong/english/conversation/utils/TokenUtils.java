package thucluong.english.conversation.utils;

import static thucluong.english.framework.utils.StringUtils.EMPTY;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.Map;
import java.util.Optional;

import thucluong.english.conversation.database.DbHelper;
import thucluong.english.framework.database.model.AppSettings;
import thucluong.english.framework.utils.StringUtils;
import thucluong.english.framework.utils.constant.AppSettingsKey;
import thucluong.english.framework.utils.constant.RequestHeader;

public class TokenUtils {
  private TokenUtils() {

  }

  public static boolean injectToken(Map<String, String> headers, Response.ErrorListener errorCallBack) {
    String token = getToken();
    if (StringUtils.isEmpty(token)) {
      errorCallBack.onErrorResponse(new AuthFailureError("Token is not existed"));
      return false;
    }
    headers.put(RequestHeader.AUTHORIZATION, RequestHeader.BEARER + " " + token);
    return true;
  }

  public static String getToken() {
    return Optional.ofNullable(DbHelper.getInstance()
      .getAppSettingsDao()
      .getByName(AppSettingsKey.ACCESS_TOKEN)).map(AppSettings::getValue).orElse(EMPTY);
  }
}
