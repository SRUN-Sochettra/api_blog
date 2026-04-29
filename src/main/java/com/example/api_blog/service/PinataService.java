package com.example.api_blog.service;

import com.example.api_blog.model.entity.MultipartInputStreamFileResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class PinataService {

    @Value("${pinata.api-key}")
    private String apiKey;

    @Value("${pinata.secret-api-key}")
    private String secretKey;

    // FIXED: declare as bean or instantiate cleanly
    private final RestTemplate restTemplate = new RestTemplate();

    // FIXED: correct Pinata public API URL
    private static final String PINATA_URL = "https://api.pinata.cloud/pinning/pinFileToIPFS";
    private static final String PINATA_GATEWAY = "https://gateway.pinata.cloud/ipfs/";

    public String uploadFile(MultipartFile file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("pinata_api_key", apiKey);
            headers.set("pinata_secret_api_key", secretKey);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartInputStreamFileResource(
                    file.getInputStream(),
                    file.getOriginalFilename()
            ));

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(PINATA_URL, request, Map.class);

            if (response.getBody() == null || !response.getBody().containsKey("IpfsHash")) {
                throw new RuntimeException("Invalid response from Pinata");
            }

            String cid = (String) response.getBody().get("IpfsHash");

            // FIXED: correct gateway URL format
            return PINATA_GATEWAY + cid;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload to Pinata: " + e.getMessage(), e);
        }
    }
}