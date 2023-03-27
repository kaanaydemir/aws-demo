package com.kaanaydemir.demo.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
public class S3Controller implements CommandLineRunner {

    private final AmazonS3 s3Client;

    public S3Controller(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    public void create() {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest("springboot-kaanaydemir");
        s3Client.createBucket(createBucketRequest);

        System.out.println("Bucket created");
    }

    public void delete() {
        try {
            DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest("springboot-kaanaydemir");
            s3Client.deleteBucket(deleteBucketRequest);
            System.out.println("Bucket deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void checkIsExist() {
        boolean exists = s3Client.doesBucketExistV2("springboot-kaanaydemir");
        System.out.println("Bucket exists: " + exists);
    }

    public void uploadFile() {
        String bucketName = "springboot-kaanaydemir";
        String objectKey = "path/to/my/file.txt";
        File file = new File("file.txt");

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, file);
        s3Client.putObject(putObjectRequest);
    }

    public void deleteBucketItems() {
        String bucketName = "springboot-kaanaydemir";
        ObjectListing objectListing = s3Client.listObjects(bucketName);

        while (true) {
            List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

            for (S3ObjectSummary objectSummary : objectSummaries) {
                s3Client.deleteObject(bucketName, objectSummary.getKey());
            }

            //We're also checking if the size of the current batch is less than 1000,
            // which is the maximum number of objects that can be deleted in a single request.
            // If the size is less than 1000, we break out of the loop
            if (objectSummaries.size() < 1000) {
                break;
            }

            objectListing = s3Client.listNextBatchOfObjects(objectListing);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        create();
        checkIsExist();
        uploadFile();
        deleteBucketItems();
        delete();
    }
}
