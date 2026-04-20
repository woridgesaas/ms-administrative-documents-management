package org.woridge.ms_administrative_documents_management.service.implementation;

import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.exception.minio.MinioBucketCreationFailedException;
import org.woridge.ms_administrative_documents_management.service.MinioBucketService;
import org.woridge.ms_administrative_documents_management.service.MinioBucketValidator;

@Service
@AllArgsConstructor
public class MinioBucketServiceImpl implements MinioBucketService {

    private final MinioClient minioClient;
    private final MinioBucketValidator minioBucketValidator;

    @Override
    public void createBucket(String bucketName) {

        boolean exists = minioBucketValidator.isBucketExists(bucketName);

        if (!exists) {

            try {

                minioClient.makeBucket(
                        MakeBucketArgs
                                .builder()
                                .bucket(bucketName)
                                .build()
                );

            } catch (Exception exception) {

                throw new MinioBucketCreationFailedException("Failed to create bucket : " + bucketName);
            }
        }
    }
}
