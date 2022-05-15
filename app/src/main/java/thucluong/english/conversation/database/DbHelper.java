package thucluong.english.conversation.database;

import thucluong.english.framework.database.BaseDbHelper;

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
}
