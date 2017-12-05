package com.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Msg> msgList= new ArrayList<>();
    private EditText inputEdit;
    private Button send;
    RecyclerView recyclerView;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputEdit= findViewById(R.id.input_edit);
        send= findViewById(R.id.send);
        recyclerView= findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        msgAdapter= new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content= inputEdit.getText().toString();
                if (!content.equals("")) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    msgAdapter.notifyItemChanged(msgList.size() - 1);//当有新消息时，刷新显示
                    recyclerView.scrollToPosition(msgList.size() - 1); //将recyclerView定位到最后一行
                    inputEdit.setText("");
                }
            }
        });
    }

    public void initMsg(){
        Msg msg1= new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2= new Msg("Hello .Who is that?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3= new Msg("This is Tom. Nice talking to you",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
