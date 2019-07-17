package com.example.mobile;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Info implements Serializable {
    private int id;
    private String source;
    private String href;
    private String name;
    private Date time;
    private int popularity;
    private boolean read;



    public static ArrayList<Info> getAllFromBD(){
        HttpRequests httpRequests = new HttpRequests();
        httpRequests.start();

        ArrayList<Info> infos = new ArrayList<>();
        while (httpRequests.resp==null){ }
        String[] nums = httpRequests.resp.split("\n");
        for (int i = 0; i<nums.length/3;i++){

            Info info = new Info();
            info.setName(nums[i*3+2]);
            info.setHref(nums[i*3+1]);
            infos.add(info);
        }
        return infos;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("source", source);
        result.put("href", href);
        result.put("name", name);
        result.put("time", time);
        result.put("popularity", popularity);
        result.put("read", read);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return id == info.id &&
                popularity == info.popularity &&
                read == info.read &&
                source.equals(info.source) &&
                href.equals(info.href) &&
                name.equals(info.name) &&
                time.equals(info.time);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, source, href, name, time, popularity, read);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
