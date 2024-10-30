package ies.tsds.letscooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_IMAGE = "image"; // Añade esta línea


    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_DESCRIPTION = "description";
    public static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
    public static final String COLUMN_RECIPE_IMAGE = "image";
    public static final String COLUMN_RECIPE_USER_ID = "user_id"; // Para relacionar con el usuario

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT UNIQUE,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_IMAGE + " TEXT" + // Añade esta línea para la imagen
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Crear tabla de recetas
        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_RECIPE_NAME + " TEXT,"
                + COLUMN_RECIPE_DESCRIPTION + " TEXT,"
                + COLUMN_RECIPE_INGREDIENTS + " TEXT,"
                + COLUMN_RECIPE_IMAGE + " TEXT,"
                + COLUMN_RECIPE_USER_ID + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_RECIPE_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "))";
        db.execSQL(CREATE_RECIPES_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public boolean checkUser(String email, String password) {
        // Verificar si el usuario es admin
        if ("admin@example.com".equals(email) && "admin".equals(password)) {
            return true; // Permitir acceso sin registro
        }

        // Verificar en la base de datos
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }

    public void addRecipe(String name, String description, String ingredients, String image, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, name);
        values.put(COLUMN_RECIPE_DESCRIPTION, description);
        values.put(COLUMN_RECIPE_INGREDIENTS, ingredients);
        values.put(COLUMN_RECIPE_IMAGE, image);
        values.put(COLUMN_RECIPE_USER_ID, userId);
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }
    public String getUserName(int userId) {
        String name = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME + " FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + "=?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return name;
    }

    public String getUserEmail(int userId) {
        String email = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_EMAIL + " FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + "=?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            email = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return email;
    }

    public String getUserImagePath(int userId) {
        String imagePath = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT image FROM " + TABLE_USERS + " WHERE id=?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            imagePath = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return imagePath;
    }

    public void updateUserProfile(int userId, String name, String email, String imagePath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        if (imagePath != null) {
            values.put(COLUMN_IMAGE, imagePath); // Asegúrate de que COLUMN_IMAGE esté definido
        }
        db.update(TABLE_USERS, values, COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        db.close();
    }



}