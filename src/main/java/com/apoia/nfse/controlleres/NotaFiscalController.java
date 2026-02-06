package com.apoia.nfse.controlleres;
import com.apoia.nfse.services.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Controller
public class NotaFiscalController {

    @Autowired
    NotaFiscalService notaFiscalService;


    @GetMapping("/")
    public String index()
    {
        try {
            return "index";
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping("/xml-pdf")
    public ResponseEntity<Object> strXmlPdf(@RequestBody String xml,
                                         @RequestParam(defaultValue = "0") String chave)
    {
        try {
            byte pdf[] = notaFiscalService.gerarRelatorioPdfNFSe(xml, chave);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=" + chave + ".pdf")
                    .body(pdf);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/file-xml")
    public ResponseEntity<Object> fileXmlPdf(@RequestParam("arquivo") MultipartFile arquivo)
    {
        try {
            if (arquivo.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo vazio");
            }
            String chave = arquivo.getOriginalFilename().replaceFirst("\\.xml$", "");
            String xml = new String(arquivo.getBytes(), StandardCharsets.UTF_8);
            byte pdf[] = notaFiscalService.gerarRelatorioPdfNFSe(xml, chave);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=" + chave + ".pdf")
                    .body(pdf);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}


