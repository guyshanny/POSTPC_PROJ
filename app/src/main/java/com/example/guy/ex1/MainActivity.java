package com.example.guy.ex1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Main activity class
 */
public class MainActivity extends AppCompatActivity
{
    // Messages container's obj
    public ArrayList<ChatMessage> _chatMessages;

    /**
     * Randomize a RGB color for the text paining
     * If random results a WHITE color, it tries again
     * @return A random RGB color which is not WHITE
     */
    private Integer _rndColor()
    {
        Integer color;
        Random rnd = new Random();

        do {
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        } while (color == Color.WHITE);

        return color;
    }

    /**
     * Saves current state's variables after onDestroy (e.g  on screen orientation)
     * @param outState bundle in which to place our saved state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("_chatMessages", _chatMessages);
    }

    /**
     * Handles the activity creation
     * @param savedInstanceState the activity's saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if don't need to restore objects
        if (null == savedInstanceState)
        {
            _chatMessages = new ArrayList<ChatMessage>();
            ChatMessage._colorBase = new HashMap<String, Integer>();
        }
        else    // If objects were restored, we populate list view again
        {
            _chatMessages = savedInstanceState.getParcelableArrayList("_chatMessages");
            _populateListView();
        }

        // Sets a button event listener
        final Button sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            /**
             * This function handles clicking on the "Send" button
             * @param v the button's view obj
             */
            @Override
            public void onClick(View v)
            {
                final EditText nameText = (EditText)findViewById(R.id.nameText);
                final EditText messageText = (EditText)findViewById(R.id.messageText);
                final String name = nameText.getText().toString();

                // Handle color mechanism
                if (!(ChatMessage._colorBase.containsKey(name)))
                {
                    ChatMessage._colorBase.put(name, _rndColor());
                }

                // Update internals
                MainActivity.this._chatMessages.add(new ChatMessage(name,
                                                                    messageText.getText().toString(),
                                                                    ChatMessage._colorBase.get(name)));

                // Update view
                _populateListView();
            }
        });
    }

    /**
     * This function handles populating the view list data
     * @return None
     */
    private void _populateListView()
    {
        //String[] items = _chatMessages.toArray(new String[_chatMessages.size()]);

        String[] items = new String[this._chatMessages.size()];
        for (int i = 0 ; i < this._chatMessages.size() ; i++)
        {
            items[i] = this._chatMessages.get(i).toString();
        }

        // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                       // Context for the activity
                R.layout.chat_messages,     // Layout to use (create)
                items                       // Items to be displayed
        ) {
            /**
             * This function handles a color manipulation on the view's text
             * We use it to color the chat message for a given chatter
             * @param position the position of the view
             * @param convertView the converted view
             * @param parent the parent of the view
             * @return the view
             */
              @Override
              public View getView(final int position, View convertView, ViewGroup parent)
              {
                  TextView textView = (TextView)super.getView(position, convertView, parent);
                  try
                  {
                      String name = textView.getText().toString().split(":")[0].trim();
                      textView.setTextColor(ChatMessage._colorBase.get(name));
                  }
                  catch (Exception e)
                  {
                      textView.setTextColor(Color.BLACK);
                  }

                  return textView;
              }
        };

        // Configure the list view
        ListView list = (ListView)findViewById(R.id.chatMessageList);
        list.setAdapter(adapter);
    }
}
