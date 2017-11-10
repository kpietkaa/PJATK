package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TasksDB";
    private static final String TABLE_NAME = "Tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static final String[] COLUMNS = {
                                              KEY_ID,
                                              KEY_NAME,
                                              KEY_PRICE,
                                              KEY_QUANTITY };

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Tasks ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
                + "price INTEGER, " + "quantity INTEGER )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] { String.valueOf(task.getId()) });
        db.close();
    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                                 COLUMNS,
                                 " id = ?",
                                 new String[] { String.valueOf(id) },
                                 null,
                                 null,
                                 null,
                                 null);

        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task();
        task.setId(Integer.parseInt(cursor.getString(0)));
        task.setName(cursor.getString(1));
        task.setPrice(Integer.parseInt(cursor.getString(2)));
        task.setQuantity(Integer.parseInt(cursor.getString(3)));
        return task;
    }


    public List<Task> allTasks() {
        List<Task> tasks = new LinkedList<Task>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Task task = null;

        if (cursor.moveToFirst()) {
            do {
                task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setPrice(Integer.parseInt(cursor.getString(2)));
                task.setQuantity(Integer.parseInt(cursor.getString(3)));
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        return tasks;
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_PRICE, task.getPrice());
        values.put(KEY_QUANTITY, task.getQuantity());

        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_PRICE, task.getPrice());
        values.put(KEY_QUANTITY, task.getQuantity());

        int i = db.update(TABLE_NAME,
                          values,
                          "id = ?",
                          new String[] { String.valueOf(task.getId()) });
        db.close();

        return i;
    }
}
