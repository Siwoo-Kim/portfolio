package com.siwoo.document_application;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.repository.DocumentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DocumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentApplication.class, args);
	}


	@Bean
	CommandLineRunner addDocuments(DocumentRepository documentRepository){
		return args->{
			documentRepository.saveAll(
					Arrays.asList(
						new Document("title1","text1",false,null,10, Arrays.asList("tag1","tag2","tag3"),null),
						new Document("title1","text1",false,null,10, Arrays.asList("tag1","tag2","tag3"),null),
						new Document("title1","text1",false,null,10, Arrays.asList("tag1","tag2","tag3"),null),
						new Document("title1","text1",false,null,10, Arrays.asList("tag1","tag2","tag3"),null),
						new Document("title1","text1",false,null,10, Arrays.asList("tag1","tag2","tag3"),null),
						new Document("title1","text1",false,null,10, Arrays.asList("tag1","tag2","tag3"),null)
					));
		};
	}

}
