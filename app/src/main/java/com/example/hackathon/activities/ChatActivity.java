package com.example.hackathon.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon.R;
import com.example.hackathon.adapters.MessageAdapter;
import com.example.hackathon.models.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;

    MessageAdapter messageAdapter;

    ImageView backBtn;


    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        messageList = new ArrayList<>();

        recyclerView = findViewById(R.id.chatRecyclerView);
        welcomeTextView = findViewById(R.id.welcome_text);
        messageEditText = findViewById(R.id.chatEditText);
        sendButton = findViewById(R.id.chatSendBtn);

        backBtn = findViewById(R.id.backbtn);

        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        sendButton.setOnClickListener((v) -> {
            String question = messageEditText.getText().toString().trim();
            addToChat(question, Message.SENT_BY_ME);
            messageEditText.setText("");
            callAPI(question);
            welcomeTextView.setVisibility(View.GONE);
        });


        backBtn.setOnClickListener((v) -> {
            finish();
        });


    }


    void addToChat(String message, String sentBy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message, sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response) {
        messageList.remove(messageList.size() - 1);
        addToChat(response, Message.SENT_BY_BOT);
    }

    void callAPI(String question) {
        //okhttp
        messageList.add(new Message("Typing... ", Message.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("model", "gpt-3.5-turbo");

            JSONArray messageArr = new JSONArray();

            JSONObject config = new JSONObject();
            config.put("role", "system");
            config.put("content", "You are a helpful assistant for a dermatology mobile app named DermBot.");

            JSONObject msgObj = new JSONObject();
            msgObj.put("role", "user");
            msgObj.put("content", question);

            messageArr.put(config);
            messageArr.put(msgObj);

            jsonBody.put("messages", messageArr);

            Log.i("msg",jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer YOUR_API_KEY")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> addResponse("Failed to load response due to " + e.getMessage()));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        if (jsonArray.length() > 0) {
                            String result = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                            runOnUiThread(() -> addResponse(result.trim()));
                        } else {
                            runOnUiThread(() -> addResponse("Empty response from the server."));
                        }
                    } else {
                        runOnUiThread(() -> addResponse("Failed to load response. HTTP code: " + response.code()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> addResponse("Error parsing JSON response"));
                }
            }
        });

    }


}
