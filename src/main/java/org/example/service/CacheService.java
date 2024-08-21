package org.example.service;

import org.example.entity.CacheTable;
import org.example.repo.CacheTableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.cache.Cache;
import javax.cache.CacheManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CacheService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CacheTableRepository cacheRepo;

    public Map<String, String> getQueryValues(String programName){
        String cacheName;
        if("FORM3".equals(programName))
            cacheName="form3cache";
        else
            cacheName="gbsCache";
        Map<String, String> cacheMap= new HashMap<>();
        Cache<String, String> form3Cache = cacheManager.getCache(cacheName);
        if (null!=form3Cache){
            CacheTable cacheTable=cacheRepo.findByQueryName("refreshTime");
            if(null!=form3Cache.get("loadTime")) {
                if(null!=cacheTable && null!= cacheTable.getQueryvalue() && (LocalDateTime.parse(form3Cache.get("loadTime")).isBefore(LocalDateTime.parse(cacheTable.getQueryvalue())))){
                    return loadCache(programName, form3Cache);
                }
                else {
                    return getFromCache(form3Cache);
                }
            }
            else
                return loadCache(programName, form3Cache);
        }

        return null;
    }

    public Map<String, String> loadCache(String programName, Cache<String, String> cache){
        List<CacheTable> cacheList = cacheRepo.findByProgramName(programName);
        if(!CollectionUtils.isEmpty(cacheList)) {
            cacheList.forEach(l -> cache.put(l.getQueryname(), l.getQueryvalue()));
            String loadTime = String.valueOf(LocalDateTime.now());
            cache.put("loadTime", loadTime);
            Map<String, String> cacheMap = cacheList.stream().collect(Collectors.toMap(CacheTable::getQueryname, CacheTable::getQueryvalue));
            cacheMap.put("loadTime", loadTime);
            return cacheMap;
        }
        return null;
    }

    public Map<String, String> getFromCache(Cache<String, String> cache){
        Set<String> keys = new HashSet<>();
        cache.forEach(entry -> keys.add(entry.getKey()));
        Map<String, String> cacheMap= new HashMap<>();
        keys.forEach( k -> {
            cacheMap.put(k, cache.get(k));
        });
        return cacheMap;
    }

    public String clearFromCache(String programName){
        String cacheName;
        if("FORM3".equals(programName))
            cacheName="form3cache";
        else
            cacheName="gbsCache";
        Cache<String, String> form3Cache = cacheManager.getCache(cacheName);
        form3Cache.removeAll();
        String refreshTime=String.valueOf(LocalDateTime.now());
        cacheRepo.save(CacheTable.builder().programname(programName).queryname("refreshTime").queryvalue(refreshTime).build());
        return refreshTime;
    }
}
