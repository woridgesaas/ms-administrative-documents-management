package org.woridge.ms_administrative_documents_management.service.implementation;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.exception.minio.MinioUploadPdfDocumentFailedException;
import org.woridge.ms_administrative_documents_management.service.MinioBucketService;
import org.woridge.ms_administrative_documents_management.service.MinioService;
import org.woridge.ms_administrative_documents_management.utils.MinioUtils;

import java.io.ByteArrayInputStream;

@Service
public class MinioServiceImpl implements MinioService {

    @Value("${minio.service-identifier}")
    private String SERVICE_IDENTIFIER;

    private final MinioClient minioClient;
    private final MinioBucketService minioBucketService;

    public MinioServiceImpl(MinioClient minioClient, MinioBucketService minioBucketService) {
        this.minioClient = minioClient;
        this.minioBucketService = minioBucketService;
    }

    @Override
    public String uploadPdfDocument(String tenantName, String fileName, byte[] content) {

        String bucketName = MinioUtils.buildBucketName(tenantName, SERVICE_IDENTIFIER);

        minioBucketService.createBucket(bucketName);

        try {
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(
                                    new ByteArrayInputStream(content),
                                    content.length,
                                    -1
                            )
                            .contentType("application/pdf")
                            .build()
            );

            return bucketName + "/" + fileName;

        } catch (Exception e) {

            throw new MinioUploadPdfDocumentFailedException("Failed to upload PDF document : " + fileName + " to bucket : '" + bucketName);
        }
    }
}