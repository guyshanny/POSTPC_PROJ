package com.example.guy.ex1;

/**
 * Created by GUY on 3/18/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a container for the chat message
 * Pay attention - it implements Parcelable so we could save it's state
 */
public class ChatMessage implements Parcelable
{
    public static HashMap<String, Integer> _colorBase;

    private String _name;           // Chatter name
    private String _message;        // Chatter message
    private Integer _color;         // Chatter's color

    /**
     * C'tor for this obj
     * @param name the chatter's name
     * @param message the message
     * @param color the color of the chatter's messages
     */
    ChatMessage(String name, String message, Integer color)
    {
        this._name = name;
        this._message = message;
        this._color = color;
    }

    /**
     * C'tor for recreation after onDestroy
     * @param in a stream handler in which we saved our state
     */
    private ChatMessage(Parcel in)
    {
        _name = in.readString();
        _message = in.readString();
        _color = in.readInt();

        // Retrieving and saving HashMap
        final Integer size = in.readInt();
        for (int i = 0 ; i < size ; i++)
        {
            final String key = in.readString();
            final Integer value = in.readInt();
            _colorBase.put(key, value);
        }
    }

    /**
     * Handles the "toString" of this obj
     * @return the string represents the obj
     */
    @Override
    public String toString()
    {
        return this._name + ":  " + this._message;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    /**
     * Saves object in parcel
     * @param dest destination obj
     * @param flags save flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_name);
        dest.writeString(_message);
        dest.writeInt(_color);

        // Writing HashMap
        dest.writeInt(_colorBase.size());

        for (Map.Entry<String, Integer> entry : _colorBase.entrySet())
        {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue());
        }
    }

    /**
     * A creator for the loading operation that is being triggered in onCreate
     * while we reconstruct our internal params
     */
    public static final Parcelable.Creator<ChatMessage> CREATOR = new Parcelable.Creator<ChatMessage>()
    {
        /**
         * Creates the object from a given stream
         * @param in a handler to a stream we've saved our state in
         * @return a ChatMessage obj filled with the state in 'in'
         */
        public ChatMessage createFromParcel(Parcel in)
        {
            return new ChatMessage(in);
        }

        /**
         * C'tor for an array of obj. Needed as a part of the Parcelable abstraction
         * @param size of array
         * @return an array of ChatMessage obj
         */
        public ChatMessage[] newArray(int size)
        {
            return new ChatMessage[size];
        }
    };
}