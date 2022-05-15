package thucluong.english.conversation.database;

import androidx.room.Database;

import thucluong.english.framework.database.BaseRoomDataBase;
import thucluong.english.framework.database.model.AppSettings;

@Database(entities = {AppSettings.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends BaseRoomDataBase {
  public static final String DATABASE_NAME = "english_conversation";
}
