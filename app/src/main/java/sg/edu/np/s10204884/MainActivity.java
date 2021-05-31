package sg.edu.np.s10204884;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    User u;
    boolean f ;
    Button followButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Replace Heading with Intent int
        Intent receivedInfo = getIntent();
        String p = receivedInfo.getStringExtra("position");
        int i = Integer.parseInt(p);
        u = ListActivity.userList.get(i);
        TextView heading = findViewById(R.id.textView3);
        heading.setText(u.getName()); //concatenation
        TextView descText = findViewById(R.id.loremText);
        descText.setText(u.getDescription());
        f = (u.getFollowed());
        UserDBHandler db= new UserDBHandler(this);
        followButton = findViewById(R.id.btnFollow);
        if(f){
            followButton.setText("UNFOLLOW");

        }
        else{

            followButton.setText("FOLLOW");

        }
       followButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               String toastText = "";
               String followText = "";
               int num ;
               boolean newFollowed;
               if (followButton.getText().toString().equals("FOLLOW")) {
                   followText = "UNFOLLOW";
                   num = 1;
                   toastText = "Followed";
                   newFollowed = true;
               }
               else{
                   followText = "FOLLOW";
                   num = 0;
                   toastText = "Unfollowed";
                   newFollowed = false;
               }
               u.setFollowed(newFollowed);//update value in for current User object during ongoing app session
               followButton.setText(followText);
               db.UpdateUser(u.getName(),num);//live update on db also
               showToast(toastText);
           }
       });


    }
    public void showToast(String toastText){
        //generate Toast notification first
        Toast toast = Toast.makeText(getApplicationContext(),toastText,Toast.LENGTH_SHORT);
        toast.show();

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug","Destroy");
    }

    @Override
    protected void onPause() {
        Log.d("debug","Pause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("debug","Resume");

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("debug","restart"); //print command to android
    }


}