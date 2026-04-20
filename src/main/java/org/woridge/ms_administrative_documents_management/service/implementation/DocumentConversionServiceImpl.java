package org.woridge.ms_administrative_documents_management.service.implementation;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import org.woridge.ms_administrative_documents_management.exception.document.DocumentConversionFailedException;
import org.woridge.ms_administrative_documents_management.service.DocumentConversionService;

import java.io.ByteArrayOutputStream;

@Service
public class DocumentConversionServiceImpl implements DocumentConversionService {

    @Override
    public byte[] convertHtmlToPdf(String html) {

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(out);
            builder.run();

            return out.toByteArray();

        } catch (Exception e) {

            throw new DocumentConversionFailedException("Failed to convert HTML to PDF");
        }
    }
}
