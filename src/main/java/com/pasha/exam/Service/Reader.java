package com.pasha.exam.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Reader implements Runnable {

    private Urls urls;
    private Writer writer;

    public Reader(Urls urls, Writer writer) {
        this.urls = urls;
        this.writer = writer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            String url = urls.getNextUrl();

            if (url == null) {
                writer.disconnectReader();
                return;
            }
            try {
                String data = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
                writer.addUrl(url, data);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
