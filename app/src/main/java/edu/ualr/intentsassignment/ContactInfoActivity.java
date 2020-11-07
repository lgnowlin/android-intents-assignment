package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import edu.ualr.intentsassignment.databinding.ActivityInfoBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step
    private Chip callButton;
    private Chip textButton;
    private Chip emailButton;
    private Chip mapButton;
    private Chip websiteButton;
    private ActivityInfoBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mBinding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        callButton = findViewById(R.id.callChip);
        textButton = findViewById(R.id.textChip);
        emailButton = findViewById(R.id.emailChip);
        mapButton = findViewById(R.id.mapChip);
        websiteButton = findViewById(R.id.websiteChip);
        TextView name = findViewById(R.id.textName);
        final TextView phoneNum = findViewById(R.id.textPhone);
        final TextView emailAdd = findViewById(R.id.textEmailAddress);
        final TextView address = findViewById(R.id.textAddress);
        final TextView website = findViewById(R.id.textWebsite);
        name.setText(c.getFullName());
        phoneNum.setText(c.getPhoneNumber());
        emailAdd.setText(c.getEmailAddress());
        address.setText(c.getAddress());
        website.setText(c.getWebsite());

        this.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallClick(phoneNum);
            }
        });
        this.textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTextClick(phoneNum);
            }
        });
        this.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmailClick(emailAdd);
            }
        });
        this.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMapClick(address);
            }
        });
        this.websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWebsiteClick(website);
            }
        });

        }

    private void onCallClick(TextView phoneNum) {
        String phoneNumberUri = "tel:" + phoneNum.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }

    private void onTextClick(TextView phoneNum) {
        String textUri = "smsto:" + phoneNum.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(textUri));
        intent.putExtra("message", "Hello!");
        startActivity(intent);
    }

    private void onEmailClick(TextView emailAdd) {
        String emailSubject = "Hello";
        String emailText = "Hello!";
        String emailReceiverList[] = {emailAdd.getText().toString()};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(Intent.createChooser(intent, "To complete action choose:"));
    }

    private void onMapClick(TextView address) {
        String addressUri = String.format("geo:0,0?q=(%s)", address.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(addressUri));
        startActivity(intent);
    }

    private void onWebsiteClick(TextView website) {
        String webUri = website.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(intent);
    }

}
