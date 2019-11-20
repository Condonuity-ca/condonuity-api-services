package tech.torbay.fileservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.torbay.fileservice.service.AzureBlobService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileServiceController {

    @Autowired
    private AzureBlobService azureBlobService;

    @PostMapping("/container")
    public ResponseEntity createContainer(@RequestBody String containerName){
        boolean created = azureBlobService.createContainer(containerName);
        return ResponseEntity.ok(created);
    }

    @PostMapping
    public ResponseEntity upload(@RequestParam MultipartFile multipartFile){
        URI url = azureBlobService.upload(multipartFile);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/blobs")
    public ResponseEntity getAllBlobs(@RequestParam String containerName){
        List<URI> uris = azureBlobService.listBlobs(containerName);
        return ResponseEntity.ok(uris);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam String containerName, @RequestParam String blobName){
        azureBlobService.deleteBlob(containerName, blobName);
        return ResponseEntity.ok().build();
    }


}
