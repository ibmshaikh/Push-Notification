Button b=(Button)findViewById(R.id.button2);
b.b.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
            
                    String toSend_registration_token= "registertaion token of the user who recive the notification";
                    OkHttpClient mClient = new OkHttpClient();


                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(toSend_registration_token);
                    
                    sendMessage(jsonArray,"Hello","How r u","Http:\\google.com","String");


           
            
            }
      });



// ------------------------------------------Main Method-----------------------------------------//


public void sendMessage(final JSONArray recipients, final String title, final String body, final String icon, final String message) {

        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", body);
                    notification.put("title", title);
                    notification.put("icon", icon);

                    JSONObject data = new JSONObject();
                    data.put("message", message);
                    root.put("notification", notification);
                    root.put("data", data);
                    root.put("registration_ids", recipients);

                    String result = postToFCM(root.toString());
                    //Log.d("Main Activity", "Result: " + result);
                    return result;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                try {
                    JSONObject resultJson = new JSONObject(result);
                    int success, failure;
                    success = resultJson.getInt("success");
                    failure = resultJson.getInt("failure");
                    Toast.makeText(Post.this, "Message Success: " + success + "Message Failed: " + failure, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Post.this, "Message Failed, Unknown error occurred.", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    String postToFCM(String bodyString) throws IOException {

        OkHttpClient mClient = new OkHttpClient();


        final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyString);
        Request request = new Request.Builder()
                .url(FCM_MESSAGE_URL)
                .post(body)
                .addHeader("Authorization", "key=" + "Your Server API KEY Provided by firebase")
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
