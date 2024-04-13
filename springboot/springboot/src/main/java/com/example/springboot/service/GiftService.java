package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Gift;
import com.example.springboot.repository.GiftRepo;

@Service
public class GiftService {

    @Autowired
    GiftRepo GiftRepository;
    

    public Gift create(Gift gift) {
        return GiftRepository.save(gift);
    }

    public List<Gift> getAllGifts() {
        return GiftRepository.findAll();
    }

    public Optional<Gift> getGiftById(int id) {
        return GiftRepository.findById(id);
    }

    public boolean updateGift(int id, Gift gift) {
        if (!GiftRepository.existsById(id)) {
            return false;
        }
        gift.setId(id);
        GiftRepository.save(gift);
        return true;
    }

    public boolean deleteGift(int id) {
        if (!GiftRepository.existsById(id)) {
            return false;
        }
        GiftRepository.deleteById(id);
        return true;
    }

    public List<Gift> getAllGiftsSortedBy(String sortBy) {
        return GiftRepository.findAll(Sort.by(sortBy));
    }

    public Page<Gift> getAllGiftsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return GiftRepository.findAll(pageable);
    }
    public List<Gift> findByGiftName(String giftName) {
        return GiftRepository.findByGiftName(giftName);
    }

    public List<Gift> findByUserId(int userId) {
        return GiftRepository.findByUserId(userId);
    }

}
