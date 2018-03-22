package com.example.adan.mapeo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.estimote.cloud_plugin.common.EstimoteCloudCredentials;
import com.estimote.indoorsdk_module.cloud.CloudCallback;
import com.estimote.indoorsdk_module.cloud.EstimoteCloudException;
import com.estimote.indoorsdk_module.cloud.IndoorCloudManager;
import com.estimote.indoorsdk_module.cloud.IndoorCloudManagerFactory;
import com.estimote.indoorsdk_module.cloud.Location;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button) findViewById(R.id.button3);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();
        IndoorCloudManager cloudManager = new IndoorCloudManagerFactory().create(this.getApplicationContext(), new EstimoteCloudCredentials("indoor-c5x", "bf6460c5d80ac65c8b26b8ef289eea05"));
        Toast.makeText(MainActivity.this, "ingreso exitoso:"+email, Toast.LENGTH_SHORT).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);

            }
        });
        cloudManager.getLocation("Emgarcia24's location", new CloudCallback<Location>() {
            @Override
            public void success(Location location) {
                // store the Location object for later,
                // you will need it to initialize the IndoorLocationManager!
                //
                // you can also pass it to IndoorLocationView to display a map:
                // indoorView = (IndoorLocationView) findViewById(R.id.indoor_view);
                // indoorView.setLocation(location);
            }

            @Override
            public void failure(EstimoteCloudException e) {
                // oops!
            }
        });
    }
}
