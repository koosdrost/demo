package com.example.demo;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class FancyController {

    @GetMapping(value = "/conspect", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageWithMediaType() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/conspect0.jpeg");
        return IOUtils.toByteArray(in);
    }
}
