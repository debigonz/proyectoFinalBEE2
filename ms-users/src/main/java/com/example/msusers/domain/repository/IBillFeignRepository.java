package com.example.msusers.domain.repository;

import com.example.msusers.configuration.feing.OAuthFeignConfiguration;
import com.example.msusers.domain.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-bill", configuration = OAuthFeignConfiguration.class)
public interface IBillFeignRepository {

    @GetMapping("/bills/users/{id}")
    List<Bill> getBills(@PathVariable String id);
}
