package com.siwoo.document_application.web;

import com.siwoo.document_application.domain.Document;
import com.siwoo.document_application.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/doc")
public class DocumentController {

    @Autowired DocumentService documentService;

    /*============= fetch data =============*/
    @GetMapping
    @ResponseBody
    Page<Document> getDocumentsAsJson(
            @PageableDefault(
                    size = 8,
                    page = 0,
                    direction = Sort.Direction.DESC,
                    sort = {"postDate","id"}) Pageable pageable, Locale locale){
        return documentService.getDocuments(pageable);
    }


}
