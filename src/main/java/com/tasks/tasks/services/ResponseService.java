package com.tasks.tasks.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    private int status;
    private String message;
    private ArrayList<ArrayList<String>> data;

    public ResponseService status(int status) {
        this.status = status;
        return this;
    }

    public ResponseService message(String message) {
        this.message = message;
        return this;
    }

    public ResponseService data(String key, String value) {
        this.data.add(new ArrayList<String>() {
            {
                add(key);
                add(value);
            }
        });
        return this;
    }

    public String toJson() {
        return "{" +
                "\"status\":" + this.status + "," +
                "\"message\":\"" + this.message + "\"," +
                "}";
    }
}
