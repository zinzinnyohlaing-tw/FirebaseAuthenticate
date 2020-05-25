package ttes.first.firebaseauthenticate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SQLiteRegister extends AppCompatActivity {
    EditText cid,email,totalno;
    DatabaseHelper db;
    MainActivity mainActivity;
    private int totalCount=0;
    private FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite_register);
        cid=(EditText)findViewById(R.id.code);
        email=(EditText)findViewById(R.id.email);
        totalno=(EditText)findViewById(R.id.totalcount);
        db=new DatabaseHelper(this);
        mainActivity=new MainActivity();
        fAuth=FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        cid.setText(String.valueOf(db.getID("client")));

    }
    public void saveAccount(View v)
    {
        db=new DatabaseHelper(this);
        if(email.getText().toString().isEmpty() && totalno.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please Fill All Requirements!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            db.insertQuery(email.getText().toString(), totalno.getText().toString());
            Toast.makeText(this,"Save Successfully!",Toast.LENGTH_SHORT).show();

        }
        email.setText("");
        totalno.setText("");

    }
    public void updateAccount(View v)
    {
        db=new DatabaseHelper(this);
        if(email.getText().toString().isEmpty() && totalno.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please Fill The Requirements!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            db.updateQuery(cid.getText().toString(),email.getText().toString(),totalno.getText().toString());
            Toast.makeText(this,"Updated Successfully!",Toast.LENGTH_SHORT).show();

        }
        email.setText("");
        totalno.setText("");

    }
    public void deleteAccount(View v)
    {
        db=new DatabaseHelper(this);
        db.deleteQuery(cid.getText().toString());
        Toast.makeText(this,"Account is deleted!",Toast.LENGTH_SHORT).show();
        cid.setText(String.valueOf(db.getID("client")));
        email.setText("");
        totalno.setText("");
    }
    public void showAllData(View v)
    {
        db=new DatabaseHelper(this);
        Cursor result=db.getAllData();
        if(result.getCount()==0)
        {
            showDataForm("No Data is Found!");
        }
        StringBuffer buffer=new StringBuffer();
        while (result.moveToNext())
        {
            buffer.append("AccountID: "+" "+ result.getString(0)+"\n");
            buffer.append("Email: "+" "+ result.getString(1)+"\n");
            buffer.append("Total Count: "+" "+result.getString(2)+"\n");
            buffer.append("\n");
        }
       // totalCount();
        showDataForm(buffer.toString());

    }
    public void showDataForm(String message)
    {
        AlertDialog.Builder form=new AlertDialog.Builder(this);
        form.setCancelable(true);
        form.setMessage(message);
        form.show();

    }
    public void totalCount(View v)
    {
        mainActivity=new MainActivity();
        db=new DatabaseHelper(this);
        totalCount=0;
        fAuth=FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        Cursor result=db.getAllData();
        if(result.getCount()==0)
        {
            if(email.getText().toString().isEmpty() && totalno.getText().toString().isEmpty())
            {
                showDataForm("Please Fill Email!");
            }
            else
            {
                db.insertQuery(email.getText().toString(), totalno.getText().toString());
            }
        }
        else if(result.getCount()>1)
        {
            Cursor checklogin=db.checkByEmail(email.getText().toString(),totalno.getText().toString());
            if(checklogin.moveToFirst())
            {
                if(checklogin.getString(1).equals(user.getEmail()))
                {
                    int totallogin=Integer.parseInt(checklogin.getString(2));
                    totallogin++;
                    db.insertQuery(email.getText().toString(),String.valueOf(totallogin));
                }
                else
                {
                    db.insertQuery(user.getEmail(),totalno.getText().toString());
                }
            }
            StringBuffer buffer=new StringBuffer();
            if(checklogin.moveToNext())
            {
                buffer.append("AccountID: "+" "+checklogin.getString(0)+"\n");
                buffer.append("Email: "+" "+checklogin.getString(1)+"\n");
                buffer.append("Total Count: "+" "+checklogin.getString(2)+"\n");
                buffer.append("\n");
            }
            showDataForm(buffer.toString());

        }


       // Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();

    }

        /*mainActivity=new MainActivity();
        db=new DatabaseHelper(this);
        totalCount=0;
        Cursor result = db.checkByEmail(email.getText().toString());
            if (result.getCount() != 0)
            {
                if (!email.getText().toString().equals(user.getEmail()))
                {
                    totalCount++;
                    db.insertQuery(user.getEmail(),String.valueOf(totalCount));
                }
                else if (email.getText().toString().equals(user.getEmail()))
                {
                    totalCount++;
                    totalno.setText(totalCount);
                    db.insertQuery(email.getText().toString(), totalno.getText().toString());
                }
            }
            StringBuffer buffer=new StringBuffer();
            while(result.moveToNext())
            {
                buffer.append("AccountID: "+" "+result.getString(0)+"\n");
                buffer.append("Email: "+" "+result.getString(1)+"\n");
                buffer.append("Total Count: "+" "+result.getString(2)+"\n");
                buffer.append("\n");
            }
            showDataForm(buffer.toString());*/


    public void searchbyemail (View v) {
        db = new DatabaseHelper(this);
        Cursor result=db.searchByEmail(email.getText().toString());
        StringBuffer buffer=new StringBuffer();
        if(result.getCount()==0)
        {
            showDataForm("No Such Related Email Found!");
        }

        /*if (result.moveToNext())
        {
            cid.setText(result.getString(0));
            name.setText(result.getString(1));
            email.setText(result.getString(2));
            password.setText(result.getString(3));
            phone.setText(result.getString(4));

        }*/

        while (result.moveToNext())
        {
            buffer.append("AccountID:"+" "+ result.getString(0)+"\n");
            buffer.append("Email:"+" "+ result.getString(1)+"\n");
            buffer.append("Total Count:"+" "+ result.getString(2)+"\n");
            buffer.append("\n");

        }
        showDataForm(buffer.toString());

    }
    public void searchbycode(View v)
    {
        db=new DatabaseHelper(this);
        Cursor result=db.searchByID(cid.getText().toString());
        if(result.moveToNext())
        {
            cid.setText(result.getString(0));
            email.setText(result.getString(1));
            totalno.setText(result.getString(2));
        }
        if(result.getCount()==0)
        {
            Toast.makeText(this,"No Such User Found!",Toast.LENGTH_SHORT).show();
            email.setText("");
            totalno.setText("");

        }
    }

    public void cancelData(View v)
    {
        Button btncancel=(Button)findViewById(R.id.btncancel);
        email.setText("");
        totalno.setText("");

    }
}
