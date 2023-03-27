package com.kaanaydemir.demo.aws.textract;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.model.Block;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


import java.io.*;
import java.nio.ByteBuffer;
import java.util.stream.Collectors;


@Service
public class TextractService implements CommandLineRunner {

    private final AmazonTextract amazonTextract;

    public TextractService(AmazonTextract amazonTextract) {
        this.amazonTextract = amazonTextract;
    }

    public void getTextFromPdf() throws IOException {
        String documentPath = "document.pdf";
        InputStream inputStream = new FileInputStream(new File(documentPath));

        DetectDocumentTextRequest request = new DetectDocumentTextRequest()
                .withDocument(new Document()
                        .withBytes(ByteBuffer.wrap(IOUtils.toByteArray(inputStream))));

        DetectDocumentTextResult result = amazonTextract.detectDocumentText(request);

        String text = result.getBlocks()
                .stream()
                .filter(block -> block.getBlockType().equals("LINE"))
                .map(Block::getText)
                .collect(Collectors.joining("\n"));

        System.out.println(text);
    }

    @Override
    public void run(String... args) throws Exception {
        getTextFromPdf();
    }
}
