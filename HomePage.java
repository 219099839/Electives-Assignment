package za.ac.cput.toasttvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    ImageView iv;
    String st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button btnMain=findViewById(R.id.btnMainActivity);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,MainActivity.class);
                startActivity(intent);

            }});
        Button btnReg=findViewById(R.id.btnRegister);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,Registration.class);
                startActivity(intent);

            }
        });
        Button btnGoLogin=findViewById(R.id.btnLogin);

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,Login.class);
                startActivity(intent);

            }

        });
        iv= (ImageView) findViewById(R.id.imageView);
        st=getIntent().getExtras().getString("Value");
        iv.setImageDrawable(Drawable.createFromPath(st));
    }


}