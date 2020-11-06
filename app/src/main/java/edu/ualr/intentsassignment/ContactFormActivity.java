package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.model.Contact;
import edu.ualr.intentsassignment.databinding.

public class ContactFormActivity extends AppCompatActivity {
    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity
    public static final String EXTRA_CONTACT = "ContactData";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }
    public void onButtonClick(View view){
        Intent intent = new Intent(this, ContactInfoActivity.class);
        Contact c = new Contact("Gracie", "Nowlin", "501-519-1294", "lgnowlin@ualr.edu", "7 Cozy Lane, Redfield, AR", "www.ualr.edu");
        intent.putExtra(EXTRA_CONTACT, c);
        startActivity(intent);
    }
}
