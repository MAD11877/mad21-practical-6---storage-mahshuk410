package sg.edu.np.s10204884;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class UserDBHandler extends SQLiteOpenHelper {
    private static final String _TABLE = "USERS";
    private static final String Column_Id = "Id";
    private static final String Column_Username = "Username";
    private static final String Column_Desc = "Description";
    private static final String Column_Followed = "Followed ";
    public UserDBHandler(@Nullable Context context) {
        super(context, "UserDBPractical", null, 1);
    }
    //Default override functions NECESSARY onCreate,onUpgrade
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + _TABLE + "(" + Column_Id +" INTEGER PRIMARY KEY , "+ Column_Username +" TEXT,"+  Column_Desc + " TEXT,"+ Column_Followed  + " INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + _TABLE);

    }
    //own user-defined functions to read and write to database
    public void addUser(User u){
        ContentValues newUser = new ContentValues();

        newUser.put(Column_Id,u.getId());
        newUser.put(Column_Username,u.getName());
        newUser.put(Column_Desc,u.getDescription());
        newUser.put(Column_Followed,u.getFollowed());

        SQLiteDatabase userDb = getWritableDatabase();
        userDb.insert(_TABLE,null,newUser);
        userDb.close();

    }
    public ArrayList<User> getAllUsers(){
        SQLiteDatabase userDb = getWritableDatabase();
        Cursor c = userDb.rawQuery("SELECT * FROM " + _TABLE,null);

        ArrayList<User> userList = new ArrayList<User>();
        while(c.moveToNext()){
            User u = new User();
            int id = c.getInt(0);
            String Name = c.getString(1);
            String description = c.getString(2);
            int followed = c.getInt(3);

            u.setId(id);
            u.setName(Name);
            u.setDescription(description);

            Boolean f = true; //set default value
            if (followed == 0){
                f = false;
            }

            u.setFollowed(f);
            userList.add(u);
        }
        return userList;
    }
    //UPDATE USERS SET Followed = 0 WHERE Username = Name
    public void UpdateUser(String name,int followed){
        SQLiteDatabase userDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Followed",followed);
        userDb.update(_TABLE,contentValues, "Username = ?",new String[] { name});


    }
}
