package com.booksApiApplication.booksapi.services;

import com.booksApiApplication.booksapi.entity.Publisher;
import com.booksApiApplication.booksapi.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepo;

    public List<Publisher> findAll() {
        return publisherRepo.findAll();
    }

    public Publisher findById(Long id){
        return publisherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
    }

    @Transactional
    public Publisher save(Publisher publisher) {
        if (publisher.getId() == null) {
            return publisherRepo.save(publisher);
        }
        Publisher updatePublisher = publisherRepo.findById(publisher.getId())
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
        updatePublisher.setName(publisher.getName());
        updatePublisher.setAddressLine(publisher.getAddressLine());
        updatePublisher.setCity(publisher.getCity());
        updatePublisher.setState(publisher.getState());
        updatePublisher.setZip(publisher.getZip());
        return publisherRepo.save(publisher);
    }

    @Transactional
    public void delete(Long id){
        Publisher publisher = publisherRepo.findById(id)
                        .orElseThrow(()-> new RuntimeException("Invalid ID"));
        publisherRepo.delete(publisher);
    }
}
