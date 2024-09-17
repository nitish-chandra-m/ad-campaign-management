package com.github.nitish_chandra_m.ad_campaign_tool.keywords;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeywordRepository extends JpaRepository<Keyword, UUID> {
}
