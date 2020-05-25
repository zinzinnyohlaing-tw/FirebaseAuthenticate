package ttes.first.firebaseauthenticate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context)
    {
        super(context,"mydb",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client(cid INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,totalno INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public void insertQuery(String email,String totalno)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("email",email);
        values.put("totalno",totalno);
        db.insert("client",null,values);
    }
    public void updateQuery(String cid,String email,String totalno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("email",email);
        values.put("totalno",totalno);
        db.update("client",values,"cid=?",new String[]{cid});
    }
    public void deleteQuery(String cid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("client","cid=?",new String[]{cid});
    }
    public int getID(String table)
    {
        int cid=1;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("SELECT seq FROM sqlite_sequence WHERE name='client'",null);
        if(result.getCount()>0)
        {
            result.moveToFirst();
            cid=result.getInt(0)+1;
        }
        return cid;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM client",null);
        return result;
    }
    public Cursor searchByEmail(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.query("client",new String[]{"cid","email","totalno"},"email=?",new String[]{email},null,null,null);
        return result;

    }
    public Cursor searchByID(String cid)
    {
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor result=db.rawQuery("SELECT cid,email,totalno FROM client WHERE cid=?",new String[]{cid},null);
       //Cursor result=db.query("client",new String[]{"cid","email","totalno"},"cid=?",new String[]{cid},null,null,null);
       return result;
    }
    public Cursor checkByEmail(String email,String totalno)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM client WHERE email=? AND totalno=?",new String[]{email,totalno},null);
//        Cursor result=db.query("client",new String[]{"cid","email","totalno"},"email=? AND totalno=?",new String[]{"%"+email+"%","%"+totalno+"%"},null,null,null);
        return result;
    }
    public Cursor getTotalCount(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT totalno FROM client WHERE email=?",new String[]{email},null);
        return result;
    }
}
