/*package org.woridge.ms_administrative_documents_management.service.implementation;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.service.MinioService;

import java.io.InputStream;

@Service
@RequiredArgsConstructor

public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public Resource downloadFile(String objectName) {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioProperties.getBucket())
                            .object(objectName)
                            .build()
            );
            return new InputStreamResource(stream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to download file: " + objectName, e);
        }
    }
}*/