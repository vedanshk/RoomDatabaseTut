package com.example.roomdatabasetut;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabasetut.adapter.ContactAdapter;
import com.example.roomdatabasetut.db.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;
public class MainActivity extends AppCompatActivity  implements ContactAdapter.EditContact {

    FloatingActionButton floatingActionButton;
    ArrayList<Contact> contacts = new ArrayList<>();

    RecyclerView recyclerView;

    ContactAdapter contactAdapter;
    DatabaseHelper databaseHelper;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("My Contact Manager");
        databaseHelper = new DatabaseHelper(this);
        contacts = databaseHelper.getAllContacts();

        contactAdapter = new ContactAdapter(this);
        contactAdapter.setContacts(contacts);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener( v -> {
               addAndEditContacts(false , null , -1);
        });

    }

    @Override
    public void addAndEditContacts(boolean editing, Contact contact, int position) {

        View view =   LayoutInflater.from(this).inflate(R.layout.add_contact ,null , false);

        final  TextView contactTitle = view.findViewById(R.id.contactTitle);
        final EditText contactName = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle(!editing ? "New Contact" : "UpdateContact");

        if( editing && contact != null ){
            contactName.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
        }
        builder.setCancelable(true);
        builder.setPositiveButton(editing ? "Update" : "Save", (dialog, which) -> {

        });
        builder.setNegativeButton("Delete", (dialog, which) -> {

            if(editing){
                DeleteContact(contact , position);
            }else{
                dialog.cancel();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener( v ->{
            String name = contactName.getText().toString();
            String email = contactEmail.getText().toString();

            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email)){
                Toast.makeText(MainActivity.this, "Please Enter Valid email and name", Toast.LENGTH_SHORT).show();
                return;
            }else{

                alertDialog.dismiss();
            }

            if(editing && contact != null ){

                UpdateContact(name , email , position);

            }else{

                CreateContact(name , email);
            }

        });

    }

    private void CreateContact(String name, String email) {

        int id = (int) databaseHelper.insertContact(name , email);

        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setId(id);
        contacts.add(contact);

        contactAdapter.notifyDataSetChanged();


    }

    private void UpdateContact(String name, String email, int position) {
        Contact contact  = contacts.get(position);
        contact.setName(name);
        contact.setEmail(email);

        databaseHelper.updateContact(contact);
        contactAdapter.notifyDataSetChanged();
    }

    private void DeleteContact(Contact contact, int position) {

        contacts.remove(position);
        databaseHelper.deleteContact(contact);

        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.contact_setting){
            Toast.makeText(this, "Hello from setting ", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}