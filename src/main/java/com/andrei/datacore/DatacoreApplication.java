package com.andrei.datacore;

import java.util.concurrent.Executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.andrei.datacore.service.FileInformationService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class DatacoreApplication {

	private static final int NUMBER_OF_FILES = 100;

	private static final int POLL_SIZE = 2;

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(POLL_SIZE);
		executor.setMaxPoolSize(POLL_SIZE);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("DataCoreThread-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(DatacoreApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FileInformationService fileInformationService) {

		return args -> {
			log.debug("INICIANDO APLICAÇÃO");

			for (int i = 0; i < NUMBER_OF_FILES; i++) {
				fileInformationService.createFile(i + 1);
			}
		};

	}

}
