package thucluong.english.conversation;

import static thucluong.english.conversation.common.Constant.HOST;
import static thucluong.english.conversation.common.Constant.LOGIN_RESULT;
import static thucluong.english.conversation.common.Constant.VIEW_SCREEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import thucluong.english.conversation.database.DbHelper;
import thucluong.english.conversation.enums.ScreenView;
import thucluong.english.conversation.utils.TokenUtils;
import thucluong.english.framework.activity.BaseActivity;
import thucluong.english.framework.api.service.impl.LoginApiService;

public class MainActivity extends BaseActivity implements Response.Listener<String> {

  private Button btnLogin;
  private Button btnSlide;

  private final LoginApiService loginApiService;
  public MainActivity() {
    this.loginApiService = new LoginApiService(HOST);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    DbHelper.setApplicationContext(this.getApplicationContext());

    btnLogin = findViewById(R.id.main_btn_login);
    btnLogin.setOnClickListener(this);
    btnLogin.setVisibility(View.GONE);

    btnSlide = findViewById(R.id.main_btn_slide);
    btnSlide.setOnClickListener(this);
    btnSlide.setVisibility(View.GONE);

    loginApiService.checkSession(this, TokenUtils.getToken(), this, this);
  }

  @Override
  public void onResponse(String response) {
    if (response.equals("OK")) {
      updateLayout(true);
    }
  }

  @Override
  public void onErrorResponse(VolleyError error) {
    btnLogin.setVisibility(View.VISIBLE);
    updateLayout(false);
    super.onErrorResponse(error);
  }

  private void updateLayout(boolean isLogged) {
    if (isLogged) {
      btnLogin.setVisibility(View.GONE);
      btnSlide.setVisibility(View.VISIBLE);
    } else {
      btnLogin.setVisibility(View.VISIBLE);
      btnSlide.setVisibility(View.GONE);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.main_btn_login:
        Intent intentLogin = new Intent(this.getApplicationContext(), LoginActivity.class);
        activityResultLauncher.launch(intentLogin);
        break;
      case R.id.main_btn_slide:
        Intent intentWorkGroup = new Intent(this.getApplicationContext(), SlideActivity.class);
        intentWorkGroup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intentWorkGroup);
        break;
      default:
        break;
    }
  }

  @Override
  protected void subViewCallBackHandler(ActivityResult result) {
    Intent intent = result.getData();
    assert intent != null;
    ScreenView viewScreen = (ScreenView) intent.getSerializableExtra(VIEW_SCREEN);
    if (viewScreen == ScreenView.LOGIN) {
      updateLayout(intent.getBooleanExtra(LOGIN_RESULT, false));
    }
  }
}