package com.swe.lms.admin.api.repository;

import com.swe.lms.admin.api.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer>  {
}
