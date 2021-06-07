package com.pasha.exam.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class XmlCreatorService {
    final String path = "urls.txt";

    @Value("${threads_count}")
    private int threadsCount;

    public void process() {
        Urls urls = new Urls(readUrls());
        Writer writer = new Writer(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            new Reader(urls, writer);
        }
    }

    private List<String> readUrls() {
        List<String> urls = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()))) {
            String str;
            while ((str = br.readLine()) != null) {
                urls.add(str);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
