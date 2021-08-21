package com.example.vacq;

import com.google.gson.annotations.SerializedName;

public class Priority {
    @SerializedName("prediction")
    int[] priority;

    public void setPriority(int[] priority) {
        this.priority = priority;
    }

    public int[] getPriority() {
        return priority;
    }
}
