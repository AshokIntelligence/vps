package com.app.vps.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.vps.login.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

}
