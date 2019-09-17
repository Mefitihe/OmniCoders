package com.omnicoders.omnicoders.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class GetFirebaseData {

    private String title;
    private String content;
    private String img_url;

    //Constructor

    public GetFirebaseData() {

    }

    public GetFirebaseData(String title, String content, String img_url) {

        this.title = title;
        this.content = content;
        this.img_url = img_url;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("content", content);
        result.put("img_url", img_url);


        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImgUrl() {
        return img_url;
    }
}
