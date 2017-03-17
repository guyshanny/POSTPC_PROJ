package com.example.guy.ex1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> _chatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _chatMessages = new ArrayList<String>();

        // Sets a button event listener
        final Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final EditText nameText = (EditText)findViewById(R.id.nameText);
                final EditText messageText = (EditText)findViewById(R.id.messageText);

                // Update internals
                MainActivity.this._chatMessages.add(nameText.getText().toString() + ":  " + messageText.getText().toString());

                // Update view
                _populateListView();
            }
        });
    }

    private void _populateListView()
    {
        String[] items = _chatMessages.toArray(new String[_chatMessages.size()]);

        // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                       // Context for the activity
                R.layout.chat_messages,     // Layout to use (create)
                items                       // Items to be displayed
        );

        // Configure the list view
        ListView list = (ListView)findViewById(R.id.chatMessageList);
        list.setAdapter(adapter);
    }
}
