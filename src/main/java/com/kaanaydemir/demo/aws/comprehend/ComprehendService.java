package com.kaanaydemir.demo.aws.comprehend;

import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ComprehendService implements CommandLineRunner {

    private final AmazonComprehend comprehendClient;

    public ComprehendService(AmazonComprehend comprehendClient) {
        this.comprehendClient = comprehendClient;
    }

    public void getSentiment(){
        String text = "I hate you you are the most disgusting person i ever met";
        DetectSentimentRequest request = new DetectSentimentRequest().withText(text).withLanguageCode("en");
        DetectSentimentResult result = comprehendClient.detectSentiment(request);
        String sentiment = result.getSentiment();
        System.out.println("sentiment = " + sentiment);
    }

    @Override
    public void run(String... args) throws Exception {
        getSentiment();
    }
}
