package com.example.ui_test4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.UUID;

public class add_details_page extends AppCompatActivity  {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private static final int PICK_IMG_REQUEST=1;
    private Uri imageuri;
    StorageReference storageReference;
    FirebaseAuth auth;
    private ProgressBar progressBar;

    EditText name,adress,ph_no;
    ImageView photo_upload,insert_img;
    DatabaseReference ref;
    Spinner area,location;
    ArrayAdapter<String> adapter1;
    TextView text;

    String select;
    String uuri;

    String f_n ,ad ,ph;
    int b=0,d=0;
    String uID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details_page);
        ref=FirebaseDatabase.getInstance().getReference().child("maid");
        storageReference= FirebaseStorage.getInstance().getReference("uploads");
        progressBar=findViewById(R.id.progress_bar);
        auth= FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        uID=user.getUid();

        text=findViewById(R.id.textView3);
        photo_upload = findViewById(R.id.upload_imview);
        insert_img=findViewById(R.id.image_insert);
        name = findViewById(R.id.f_name);
        adress = findViewById(R.id.address);
        ph_no = findViewById(R.id.phone_no);
        area = findViewById(R.id.area);
        location = findViewById(R.id.location);
        ArrayList<String> area_list = new ArrayList<>();
        area_list.add("select location");
        area_list.add("gurgaon");
        area_list.add("delhi");
        area_list.add("soon");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, area_list);
        area.setAdapter(adapter);
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        String select = area.getSelectedItem().toString();
                        Log.i("string is ", "" + select);
                        if (select.contentEquals("gurgaon")) {
                            ArrayList<String> gurgaon_list = new ArrayList<>();
                            gurgaon_list.add("sector_10");
                            gurgaon_list.add("sector_10A");
                            gurgaon_list.add("sector_9");
                            adapter1 = new ArrayAdapter<String>(add_details_page.this, android.R.layout.simple_spinner_dropdown_item, gurgaon_list);
                            location.setAdapter(adapter1);
                        }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i)
                {
                    case 0:
                        select= adapterView.getItemAtPosition(i).toString();
                        break;
                    case 1:
                        select= adapterView.getItemAtPosition(i).toString();
                        break;
                    case 2:
                        select= adapterView.getItemAtPosition(i).toString();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }



    public void upload() {
        f_n = name.getText().toString().trim();
        ad = adress.getText().toString().trim();
        ph = ph_no.getText().toString().trim();
        if(f_n.isEmpty())
        {
            Toast.makeText(this, "enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ad.isEmpty())
        {
            Toast.makeText(this, "enter address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ph.isEmpty())
        {
            Toast.makeText(this, "enter phone_no", Toast.LENGTH_SHORT).show();
            return;
        }
        final upload_Info info = new upload_Info(f_n, ad, ph,"active",uuri);
        if (select.equals("sector_10"))
               {
                   ref.child("area").child("gurgaon").child("sector_10").child(uID).setValue(info);
                   Toast.makeText(this, "uploaded", Toast.LENGTH_SHORT).show();
               }
               else if (select.equals("sector_10A"))
               {
                   ref.child("area").child("gurgaon").child("sector_10A").child(uID).setValue(info);
                   Toast.makeText(this, "uploaded", Toast.LENGTH_SHORT).show();
               }
               else if (select.equals("sector_9"))
               {
                   ref.child("area").child("gurgaon").child("sector_9").child(uID).setValue(info);
                   Toast.makeText(this, "uploaded", Toast.LENGTH_SHORT).show();
               }
        Intent intent = new Intent(add_details_page.this,admin_page.class);
        startActivity(intent);
        finish();
           }
           //image upload


        public void upload_btn(View view) {
            view.startAnimation(buttonClick);

            if (b == 1 && d == 1)
            {
                upload();
            }
            else
                {
                Toast.makeText(this, "please upload image", Toast.LENGTH_SHORT).show();
                }
    }

    public void upload_photo(View view) {
        b=1;
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMG_REQUEST);
        insert_img.setAlpha((float) 1.0);
        text.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMG_REQUEST  && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            imageuri = data.getData();
            Picasso.get().load(imageuri).into(insert_img);
        }
    }
   private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    public void upload_to_Database(View view) {
      if(d==0)
      {
        store_img_to_database();
      }
      else
      {
          Toast.makeText(this, "already uploaded", Toast.LENGTH_SHORT).show();
      }
        d++;
    }
    public void store_img_to_database()
    {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (imageuri != null)
        {
             StorageReference fileReference = storageReference.child(UUID.randomUUID().toString()
                    + "." + getFileExtension(imageuri));
            fileReference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            uuri=uri.toString();
                            progressBar.setVisibility(View.INVISIBLE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(add_details_page.this, "upload success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}
