package thucluong.english.conversation.database;

import static thucluong.english.framework.utils.constant.AppSettingsKey.ACCESS_TOKEN;

import thucluong.english.framework.database.BaseDbHelper;
import thucluong.english.framework.database.model.AppSettings;

public class DbHelper extends BaseDbHelper<AppDatabase> {

  private static DbHelper instance;

  public static DbHelper getInstance() {
    if (instance == null) {
      instance = new DbHelper();
    }
    return instance;
  }

  public DbHelper() {
    super(AppDatabase.class, AppDatabase.DATABASE_NAME);
  }

  public void insertOrUpdateAppSettings(String name, String value) {
    AppSettings settings = getAppSettingsDao().getByName(name);
    if (settings == null) {
      settings = new AppSettings();
      settings.setName(name);
      settings.setValue(value);
      DbHelper.getInstance().getAppSettingsDao().insert(settings);
    } else {
      settings.setValue(value);
      DbHelper.getInstance().getAppSettingsDao().update(settings);
    }
  }
}
