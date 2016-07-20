package com.example.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 文件名：
 * 描  述：
 * 作  者：Created by micw on 16/7/20 10:20
 */
public class BooksDB extends SQLiteOpenHelper {

    private final
    static String DATABASE_NAME = "BOOKS.db";

    private final
    static int DATABASE_VERSION = 1;

    private final
    static String TABLE_NAME = "books_table";

    public final
    static String BOOK_ID = "book_id";

    public final
    static String BOOK_NAME = "book_name";

    public final
    static String BOOK_AUTHOR = "book_author";

    public BooksDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "  + TABLE_NAME + " ("  + BOOK_ID

                + " INTEGER primary key autoincrement, "  + BOOK_NAME + " text, "+ BOOK_AUTHOR +" text);";

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "  + TABLE_NAME;

        db.execSQL(sql);

        onCreate(db);

    }


    public Cursor select() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        return cursor;

    }

//增加操作

    public long insert(String bookname,String author)

    {

        SQLiteDatabase db = this.getWritableDatabase();

/* ContentValues */

        ContentValues cv = new ContentValues();

        cv.put(BOOK_NAME, bookname);

        cv.put(BOOK_AUTHOR, author);

        long row = db.insert(TABLE_NAME, null, cv);

        return row;

    }

//删除操作

    public void  delete(int id)

    {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = BOOK_ID + " = ?";

        String[] whereValue ={ Integer.toString(id) };

        db.delete(TABLE_NAME, where, whereValue);

    }

//修改操作

    public void update(int id, String bookname,String author)

    {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = BOOK_ID + " = ?";

        String[] whereValue = { Integer.toString(id) };



        ContentValues cv = new ContentValues();

        cv.put(BOOK_NAME, bookname);

        cv.put(BOOK_AUTHOR, author);

        db.update(TABLE_NAME, cv, where, whereValue);

    }








}
