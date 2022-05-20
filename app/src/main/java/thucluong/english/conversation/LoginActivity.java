package thucluong.english.conversation;

import static thucluong.english.conversation.common.Constant.HOST;
import static thucluong.english.conversation.common.Constant.LOGIN_RESULT;
import static thucluong.english.conversation.common.Constant.USER_NAME;
import static thucluong.english.conversation.common.Constant.VIEW_SCREEN;
import static thucluong.english.conversation.enums.ScreenView.LOGIN;
import static thucluong.english.framework.utils.constant.AppSettingsKey.ACCESS_TOKEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;

import thucluong.english.conversation.database.DbHelper;
import thucluong.english.framework.activity.BaseActivity;
import thucluong.english.framework.api.dto.jwt.JwtResponse;
import thucluong.english.framework.api.service.impl.LoginApiService;
import thucluong.english.framework.database.model.AppSettings;
import thucluong.english.framework.utils.JsonConvertUtils;

public class LoginActivity extends BaseActivity implements Response.Listener<String> {

  private EditText txtUserName;
  private EditText txtPassword;
  private final LoginApiService loginApiService;

  public LoginActivity() {
    this.loginApiService = new LoginApiService(HOST);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    txtUserName = findViewById(R.id.txtUsername);
    txtPassword = findViewById(R.id.txtPassword);
    Button btnLogin = findViewById(R.id.btnLogin);
    btnLogin.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() != R.id.btnLogin) {
      return;
    }
    String username = txtUserName.getText().toString();
    String password = txtPassword.getText().toString();
    loginApiService.login(this, username, password, this, this);
  }

  @Override
  public void onResponse(String response) {
    JwtResponse jwtResponse = JsonConvertUtils.parseJSONToObject(response, JwtResponse.class);
    DbHelper.getInstance().insertOrUpdateAppSettings(ACCESS_TOKEN, jwtResponse.getToken());
    DbHelper.getInstance().insertOrUpdateAppSettings(USER_NAME, jwtResponse.getUsername());

    Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
    intent.putExtra(VIEW_SCREEN, LOGIN);
    intent.putExtra(LOGIN_RESULT, true);
    setResult(RESULT_OK, intent);
    finish();
  }
}