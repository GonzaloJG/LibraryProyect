package com.booksApiApplication.booksapi.config;

import com.booksApiApplication.booksapi.converters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public AuthorConverter getAuthorConverter(){
        return new AuthorConverter();
    }

    @Bean
    public PublisherConverter getPublisherConverter(){
        return new PublisherConverter();
    }

    @Bean
    public BookConverter getBookConverter(){
        return new BookConverter(getAuthorConverter(), getPublisherConverter());
    }

    @Bean
    public RegisterConverter getRegisterConverter(){ return new RegisterConverter(); }

    @Bean
    public UserConverter getUserConverter(){ return new UserConverter(); }
}
