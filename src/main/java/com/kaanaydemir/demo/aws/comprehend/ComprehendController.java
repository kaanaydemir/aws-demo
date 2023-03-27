package com.kaanaydemir.demo.aws.comprehend;

import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ComprehendController implements CommandLineRunner {

    private final AmazonComprehend comprehendClient;

    public ComprehendController(AmazonComprehend comprehendClient) {
        this.comprehendClient = comprehendClient;
    }

    public void getSentiment(){
        String text = "I really enjoyed my vacation in Hawaii. The beaches were beautiful and the weather was perfect!";
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
