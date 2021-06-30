package com.bezkoder.springjwt.services;

import org.springframework.web.multipart.MultipartFile;

public interface StockService {
   public void save(MultipartFile file);
}
