package org.example.controller;

import org.example.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CacheController {

    @Autowired
    CacheService cacheService;

    @GetMapping("/getCacheValues/{programName}")
    public ResponseEntity<Map<String, String>> getCacheValues(@PathVariable("programName") String programName){
        return ResponseEntity.ok().body(cacheService.getQueryValues(programName));
    }

    @GetMapping("/clearCacheValue/{programName}")
    public ResponseEntity<String> removeCacheValues(@PathVariable("programName") String programName){
        return ResponseEntity.ok().body("Cache cleared successfully at "+ cacheService.clearFromCache(programName));
    }

}
