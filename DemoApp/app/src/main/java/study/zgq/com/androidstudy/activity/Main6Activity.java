package study.zgq.com.androidstudy.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.view.RecyclerViewAdapter;

public class Main6Activity extends AppCompatActivity {

//    private TextView mTextMessage;
    private RecyclerView recyclerView;

    private ArrayList<Data> data = new ArrayList<>(100);

    private RecyclerViewAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
            Glide.with(Main6Activity.this).load("").into(new ImageView(Main6Activity.this));
            return false;
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++){
            data.add(new Data("" + i + "-1", "" + i + "-2"));
        }
        setContentView(R.layout.activity_main6);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
//        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.scrollToPosition(data.size() * 1000);
//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                data.set(0,new Data("tt", "0-2"));
//                adapter.notifyItemChanged(10, "tt");
                data.add(1, new Data("-1","-1"));
                adapter.notifyItemInserted(1);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewWithTag(1);
        }

    }

    public static class Data {
        public String a;
        public String b;
        public Data(String a, String b){
            this.a = a;
            this.b = b;
        }
    }

}
