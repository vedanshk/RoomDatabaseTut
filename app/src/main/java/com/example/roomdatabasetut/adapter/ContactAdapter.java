package com.example.roomdatabasetut.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabasetut.MainActivity;
import com.example.roomdatabasetut.R;
import com.example.roomdatabasetut.db.entity.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    public interface  EditContact {
        void addAndEditContacts(boolean editing , Contact contact , int position);
    }

    private final Context context;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item , parent , false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        EditContact editContact =  (MainActivity)context;

        holder.txtEmail.setText(contacts.get(position).getEmail());
        holder.txtName.setText(contacts.get(position).getName());

        holder.itemView.setOnClickListener( v ->{
            Contact contact = contacts.get(position);

            editContact.addAndEditContacts(true , contact , position);
        });
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView txtName , txtEmail;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);

        }


    }
}
