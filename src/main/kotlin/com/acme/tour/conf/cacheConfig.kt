package com.acme.tour.conf

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class cacheConfig {

    @Bean
    fun CacheManager(): CacheManager = ConcurrentMapCacheManager("promocoes")
}