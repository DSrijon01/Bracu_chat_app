package com.example.appforbrac.chatApp.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appforbrac.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private Button UpdateAccountSettings;
    private EditText username, userStatus;
    private CircleImageView userProfileImage;
    private static final int GalleryPick=1;
    private StorageReference UserProfileImagesRef;
    private DatabaseReference Rootref;
    private ProgressDialog loadingbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        Rootref = FirebaseDatabase.getInstance().getReference();

        UserProfileImagesRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        Initializefields();

        username.setVisibility(View.INVISIBLE);
        UpdateAccountSettings.setOnClickListener((View.OnClickListener) UpdateAccountSettings);






      userProfileImage.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent galleryIntent = new Intent();
             galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
             galleryIntent.setType("image/*");
             startActivityForResult(galleryIntent,GalleryPick);
         }
     });
    }



    private void Initializefields() {
        UpdateAccountSettings = (Button) findViewById(R.id.update_settings_button);
        username=(EditText) findViewById(R.id.set_user_name);
        userStatus=(EditText) findViewById(R.id.set_profile_status);
        userProfileImage=(CircleImageView) findViewById(R.id.set_profile_image);
        loadingbar = new ProgressDialog(this);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null){

         Uri ImageUri = data.getData();
         CropImage.activity()
                 .setGuidelines(CropImageView.Guidelines.ON)
                 .setAspectRatio(1,1   )
                 .start(this);

     }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode==RESULT_OK){

                loadingbar.setTitle("Set Profile image");
                loadingbar.setMessage("Profile Image Updating");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();


                Uri resultUri = result.getUri();
                StorageReference filePath = UserProfileImagesRef.child(username + ".jpg");
                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(SettingsActivity.this, "Profile Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                            final String downloadUrl = task.getResult().getMetadata().getReference().getDownloadUrl().toString();

                                    Rootref.child("Users").child(String.valueOf(username)).child("image")
                                            .setValue(downloadUrl)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){

                                                        Toast.makeText(SettingsActivity.this, "Image Save in Database, Successfully", Toast.LENGTH_SHORT).show();
                                                        loadingbar.dismiss();
                                                    }
                                                    else{
                                                        String message = task.getException().toString();
                                                        Toast.makeText(SettingsActivity.this, "Error:" +message, Toast.LENGTH_SHORT).show();
                                                        loadingbar.dismiss();
                                                    }
                                                }
                                            });
                        }
                        else{
                            String message = task.getException().toString();
                            Toast.makeText(SettingsActivity.this, "Error:" +message, Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }
                    }
                });
            }

        }

    }

    }


