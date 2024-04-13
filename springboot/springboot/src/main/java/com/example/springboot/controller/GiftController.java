package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.Gift;
import com.example.springboot.service.GiftService;

import java.util.List;

@RestController
@RequestMapping("/api/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @PostMapping
    public ResponseEntity<Gift> addGift(@RequestBody Gift gift) {
        Gift newGift = giftService.create(gift);
        return new ResponseEntity<>(newGift, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Gift>> getAllGifts() {
        List<Gift> gifts = giftService.getAllGifts();
        return new ResponseEntity<>(gifts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gift> getGiftById(@PathVariable("id") int id) {
        Gift gift = giftService.getGiftById(id).orElse(null);
        if (gift != null) {
            return new ResponseEntity<>(gift, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gift> updateGift(@PathVariable("id") int id, @RequestBody Gift gift) {
        if (giftService.updateGift(id, gift)) {
            return new ResponseEntity<>(gift, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGift(@PathVariable("id") int id) {
        if (giftService.deleteGift(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Gift>> getAllGiftsSorted(@RequestParam String sortBy) {
        List<Gift> gifts = giftService.getAllGiftsSortedBy(sortBy);
        return new ResponseEntity<>(gifts, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Gift>> getAllgiftsPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Gift> giftsPage = giftService.getAllGiftsPaginated(pageNo, pageSize);
        return new ResponseEntity<>(giftsPage, HttpStatus.OK);
    }
    @GetMapping("/name/{giftName}")
    public ResponseEntity<List<Gift>> getGiftsByGiftName(@PathVariable String giftName) {
        List<Gift> gifts = giftService.findByGiftName(giftName);
        return new ResponseEntity<>(gifts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Gift>> getGiftsByUserId(@PathVariable int userId) {
        List<Gift> gifts = giftService.findByUserId(userId);
        return new ResponseEntity<>(gifts, HttpStatus.OK);
    }
}
