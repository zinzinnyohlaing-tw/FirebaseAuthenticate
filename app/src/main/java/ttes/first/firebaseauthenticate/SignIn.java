package ttes.first.firebaseauthenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText email;
    DatabaseHelper db;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = ( EditText ) findViewById(R.id.email);
        db = new DatabaseHelper(this);
    }

    /*public void SignIn(View v) {
        db = new DatabaseHelper(this);
        Cursor result = db.checkSignIn(email.getText().toString(), password.getText().toString());
        boolean b=true;
        while(b)
        {

            if (email.getText().toString().equals("") && password.getText().toString().equals(""))
            {
                Toast.makeText(this, "Enter Email and Password!", Toast.LENGTH_SHORT).show();
            }
            else if (!email.getText().toString().equals("") && !password.getText().toString().equals(""))
            {
                count++;
                Toast.makeText(this,count+" "+ "times"+ " "+email.getText().toString()+" "+ "is sign in!",Toast.LENGTH_SHORT).show();
                if (result.getCount() != 0)
                {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
            else if (!email.getText().toString().equals(result.getString(0)) && !password.getText().toString().equals(result.getString(1)))
            {
                Toast.makeText(this, "Invalid Email and Password!", Toast.LENGTH_SHORT).show();
            }
            break;
        }

    }

    public void Cancel(View v) {
        email.setText("");
        password.setText("");
    }*/
    /*public void SignIn(View v) {
        db = new DatabaseHelper(this);
        Cursor result = db.checkSignIn(email.getText().toString());
        if (email.getText().toString().equals("") )
        {
            Toast.makeText(this, "Enter Email!", Toast.LENGTH_SHORT).show();
        }
        else if (!email.getText().toString().equals(""))
        {
//            Toast.makeText(this,count+" "+ "times"+ " "+email.getText().toString()+" "+ "is sign in!",Toast.LENGTH_SHORT).show();
            if (result.getCount() != 0)
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        else if (!email.getText().toString().equals(result.getString(0)))
        {
            Toast.makeText(this, "Invalid Email and Password!", Toast.LENGTH_SHORT).show();
        }
    }*/

}
