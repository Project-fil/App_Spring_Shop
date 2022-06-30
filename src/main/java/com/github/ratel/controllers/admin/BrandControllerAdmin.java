package com.github.ratel.controllers.admin;

import com.github.ratel.payload.request.CreateBrandRequest;
import com.github.ratel.payload.request.UpdateBrandRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SecurityRequirement(name = "Authorization")
public interface BrandControllerAdmin {

    @PostMapping(value = "brand/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createBrand(@RequestPart CreateBrandRequest createBrandRequest, @RequestPart MultipartFile file);

    @PutMapping("brand/update")
    ResponseEntity<Object> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest);

    @PutMapping(value = "brand/update/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> updateImage(@RequestPart Long brandId, @RequestPart MultipartFile file);

}
