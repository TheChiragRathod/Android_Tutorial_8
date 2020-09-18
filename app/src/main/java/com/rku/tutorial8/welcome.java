package com.rku.tutorial8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class welcome extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
    ArrayAdapter<String> adapter;


    Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        this.setTitle("Users List");
        DatabaseHelper db=new DatabaseHelper(this);
        listView=findViewById(R.id.lstUserData);
        adapter=new ArrayAdapter<String>(
                this,
                R.layout.userslist,
                db.getUsersList()
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(welcome.this,DisplayUserData.class);
                intent.putExtra("UserEmail",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });

    }

    public void SetData()
    {
        /*DatabaseHelper db=new DatabaseHelper(this);
        Cursor res=db.getData(UserEmail);
        res.moveToNext();

        txtViewFname.setText("First Name : "+res.getString(1));
        txtViewLName.setText("Last Name : "+res.getString(2));
        txtViewEmail.setText("Email : "+res.getString(3));
        txtViewpassword.setText("Password : "+res.getString(4));
        txtViewGender.setText("Gender : "+res.getString(5));
        txtViewBranch.setText("Branch : "+res.getString(6));
        txtViewCity.setText("City : "+res.getString(7));
        txtViewStatus.setText("Status : "+res.getString(8));
        */
    }




    //Logout Code In Custome menu start here.....

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.custome_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {

            case R.id.menuLogout:

                AlertDialog.Builder builder=new AlertDialog.Builder(this);

                builder.setTitle("Confirm Logout")
                        .setMessage("Do you really want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                logout();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
               alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(150,115,200)));



                break;

        }

        return super.onOptionsItemSelected(item);
    }
    //Logout Code In Custome menu finished here.....

    public void logout()
    {
        SharedPreferences SP=getSharedPreferences("Login_Info",MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();
        editor.remove("Username");
        editor.apply();

        Intent intent=new Intent(this, login.class);
        startActivity(intent);
        finish();
    }

}