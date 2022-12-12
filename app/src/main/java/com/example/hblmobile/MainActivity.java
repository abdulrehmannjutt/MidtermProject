package com.example.hblmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hblmobile.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    AFragment aFragment = new AFragment();
    BFragment bFragment = new BFragment();
    CFragment cFragment = new CFragment();
    DFragment dFragment = new DFragment();
    EFragment eFragment = new EFragment();
    FFragment fFragment = new FFragment();
    GFragment gFragment = new GFragment();
    HFragment hFragment = new HFragment();

    EditText editText1, editText2;
    Button btnsignin, btnsignup;
    Button btncheckbalance;

    Switch btnswitch;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;






    ActivityMainBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigationView.setNavigationItemSelectedListener(item ->{

            switch(item.getItemId()){
                case R.id.customers:
                    replaceFragment(new AFragment());

                    break;
            }

            return true;

                });

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        btnsignin = findViewById(R.id.SIGNIN);
        btnsignup = findViewById(R.id.SIGNUP);
        editText1 = findViewById(R.id.loginID);
        editText2 = findViewById(R.id.editTextTextPassword);
        btnswitch = findViewById(R.id.print_switch);
        btncheckbalance = findViewById(R.id.balance);

        btncheckbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(MainActivity.this, "Your Account balance is 150,000", Toast.LENGTH_SHORT).show();
            }
        });
        btnswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(btnswitch.isChecked()){

                    ///finger
                    BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                            .setTitle("Use Fingerprint to open you HBL Account")
                            .setDescription("Use your phone sensor")
                            .setNegativeButtonText("cancel")
                            .build();
                    getPrompt().authenticate(promptInfo);

                }

                else {

                }
            }
        });



        btnsignin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                String userID = editText1.getText().toString();
                String paswd = editText2.getText().toString();


                if (userID.equals("abdul") && paswd.equals("786")) {
                    openSIGNINActivity();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }

            }


        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openSIGNUPActivity();
            }
        });

    }
    public void openSIGNINActivity(){
        Intent intent = new Intent(this, SIGNINActivity.class);
        startActivity(intent);
    }

    public void openSIGNUPActivity(){
        Intent intent = new Intent(this, SIGNUPActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){


                case R.id.customers:
                    replaceFragment(aFragment);
                 // getSupportFragmentManager().beginTransaction().replace(R.id.inkro, aFragment).commit();
                  //return true;
                  Toast.makeText(this, "Customer Services", Toast.LENGTH_SHORT).show();
                  break;
                case R.id.discount:
                   // getSupportFragmentManager().beginTransaction().replace(R.id.inkro, bFragment).commit();
                    replaceFragment(bFragment);

                    Toast.makeText(this, "Discounts", Toast.LENGTH_SHORT).show();
                break;
                case R.id.konnect:
                    replaceFragment(cFragment);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.inkro, cFragment).commit();
                Toast.makeText(this, "Konnect App", Toast.LENGTH_SHORT).show();
                break;
                case R.id.installment:
                    replaceFragment(dFragment);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.inkro, dFragment).commit();
                Toast.makeText(this, "Gifts & Rewards", Toast.LENGTH_SHORT).show();
                break;
                case R.id.rewards:
                    replaceFragment(eFragment);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.inkro, eFragment).commit();
                Toast.makeText(this, "Gifts & Rewards", Toast.LENGTH_SHORT).show();
                break;
                case R.id.location:
                    replaceFragment(fFragment);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.inkro, fFragment).commit();
                Toast.makeText(this, "HBL Locator", Toast.LENGTH_SHORT).show();
                break;
                case R.id.charges:
                    replaceFragment(gFragment);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.inkro, gFragment).commit();
                Toast.makeText(this, "Charges", Toast.LENGTH_SHORT).show();
                break;
                case R.id.hblnisa:
                    replaceFragment(hFragment);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.inkro, hFragment).commit();
                Toast.makeText(this, "HBl Nisa", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;
    }

    private BiometricPrompt getPrompt(){

        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication Succeeded!");
                Intent intent = new Intent(MainActivity.this, SIGNINActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication Failed");
            }
        };
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
                return biometricPrompt;
    }

    private void notifyUser(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
 private void replaceFragment(Fragment fragment){
     FragmentManager fragmentManager = getSupportFragmentManager();
     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
     fragmentTransaction.replace(R.id.main_layout,fragment);
     fragmentTransaction.commit();
 }
}