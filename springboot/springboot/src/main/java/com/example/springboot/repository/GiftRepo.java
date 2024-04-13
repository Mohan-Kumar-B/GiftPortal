package com.example.springboot.repository;

import com.example.springboot.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GiftRepo extends JpaRepository<Gift, Integer> {

    @Query("SELECT b FROM Gift b WHERE b.giftName = ?1")
    List<Gift> findByGiftName(String giftName);
    
    @Query("SELECT b FROM Gift b WHERE b.id IN (SELECT DISTINCT b.id FROM Gift b INNER JOIN b.users u WHERE u.id = ?1)")
    List<Gift> findByUserId(int userId);
}
