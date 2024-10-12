package com.github.nitish_chandra_m.ad_campaign_tool;

import com.github.nitish_chandra_m.ad_campaign_tool.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@EnableAsync
public class AdCampaignToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdCampaignToolApplication.class, args);
	}

}
