package sg.edu.np.s10204884;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private static UserDBHandler db;
    private static UsersAdapter adapter;
    public static ArrayList<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        // Codeblock for android icon ImageView in practical 3
//        ImageView icon = findViewById(R.id.androidImg);
//        icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//               Intent in = new Intent(ListActivity.this,MainActivity.class);
//               startActivity(in);
//               Intent openDialog = getIntent();
//            }
//        });
        db= new UserDBHandler(this); //instantiate DB instance
        if (userList.size() == 0){
            for (int i = 0;i < 20;i++){
                Random rand = new Random();
                int num = 1000000001 + rand.nextInt(1000000000);
                User u = new User();
                u.setName("Name"+num);
                Random rand2 = new Random();
                int descNum = 1000000001 + rand2.nextInt(1000000000);
                u.setDescription("Description"+ descNum);
                Random followed = new Random();
                u.setId(i+1);
                u.setFollowed(followed.nextBoolean());//randomise boolean value
                db.addUser(u);

            }
        }
        userList = db.getAllUsers();
        RecyclerView rv = findViewById(R.id.rv);
        adapter = new UsersAdapter(this,userList);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("listActivity","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("listActivity","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("listActivity","Destroy");
    }

    @Override
    protected void onPause() {
        Log.d("listActivity","Pause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("listActivity","Resume");

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("listActivity","restart"); //print command to android
    }

}