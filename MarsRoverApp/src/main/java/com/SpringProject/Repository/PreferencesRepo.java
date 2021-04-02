package com.SpringProject.Repository;

import com.SpringProject.Dto.HomeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepo extends JpaRepository<HomeDto,Long> {

    HomeDto findByUserId(Long userId);
}
