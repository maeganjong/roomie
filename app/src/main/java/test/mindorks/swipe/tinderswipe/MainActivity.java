package test.mindorks.swipe.tinderswipe;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private DatabaseReference mDatabase;
    private ListView mUserList;
    private ArrayList<Profile> mUsers = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeMaxChangeAngle(2f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //mUserList = (ListView) findViewById(R.id.userList);

        //final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUserNames);
        //mUserList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Profile value = dataSnapshot.getValue(Profile.class);
                mUsers.add(value);

                String key = dataSnapshot.getKey();
                mKeys.add(key);

                loadArray();


                //arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Profile value = dataSnapshot.getValue(Profile.class);
                String key = dataSnapshot.getKey();

                int index = mKeys.indexOf(key);

                mUsers.set(index, value);

                loadArray();
                //arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        /**for (Profile profile : Utils.loadProfiles()) {
         mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));


         TextView text = (TextView) findViewById(R.id.test);
         text.setText(Utils.loadProfiles());


         findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        mSwipeView.doSwipe(false);
        }
        });

         findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        mSwipeView.doSwipe(true);
        }
        });

         findViewById(R.id.undoBtn).setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        mSwipeView.undoLastSwipe();
        }
        });
         }**/


    }

    private void loadArray() {
        for (Profile profile : mUsers) {
            mSwipeView.addView(new TinderCard(mContext, profile, mSwipeView));


            findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSwipeView.doSwipe(false);
                }
            });

            findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSwipeView.doSwipe(true);
                }
            });

            findViewById(R.id.undoBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSwipeView.undoLastSwipe();
                }
            });
        }
    }
}
