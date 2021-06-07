package com.pasha.exam.Service;

import java.util.List;
import java.util.ListIterator;

public class Urls {
    List<String> urls;
    ListIterator<String> urlsIterator;

    public Urls(List<String> urls) {
        this.urls = urls;
        this.urlsIterator = urls.listIterator();
    }

    public synchronized String getNextUrl() {
        if (urlsIterator.hasNext()) {
            return urlsIterator.next();
        }
        return null;
    }
}
