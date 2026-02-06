package com.apoia.nfse.services;

import com.apoia.nfse.models.MunicipioModel;
import com.apoia.nfse.models.NFSeXml;
import com.apoia.nfse.utils.FunctionCustom;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class NotaFiscalService {

    @Autowired
    private MunicipioService municipioService;


    public NFSeXml converterXmlNfe(String xml){
        try {
            Reader reader = new StringReader(xml);
            JAXBContext context = JAXBContext.newInstance(NFSeXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            NFSeXml nfse = (NFSeXml) unmarshaller.unmarshal(reader);
            return nfse;
        }catch (JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public byte[] gerarRelatorioPdfNFSe(String xml, String chaveAcesso) throws Exception {

        NFSeXml nf = converterXmlNfe(xml);

        if(nf == null){
            throw new RuntimeException("Erro na leitura do XML");
        }

        JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/danfse.jrxml"));
        Map<String, Object> params = new HashMap<>();

        /////// IMAGENS
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitMatrix matrix = new MultiFormatWriter().encode(
                "https://www.nfse.gov.br/ConsultaPublica/?tpc=1&chave=" + chaveAcesso,
                BarcodeFormat.QR_CODE,
                200,
                200
        );
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
        byte[] qrCodeBytes = baos.toByteArray();
        params.put("imgQrCode", new ByteArrayInputStream(qrCodeBytes));
        byte[] imgPrefeitura = Base64.getDecoder().decode("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");
        params.put("imgPrefeitura", new ByteArrayInputStream(imgPrefeitura));
        //params.put("imgPrefeitura", getClass().getResourceAsStream("/reports/danfse/IMAGEM"));

        /////// HEADER
        params.put("cdChave", chaveAcesso);
        params.put("nNFSe", nf.getInfNFSe().getNNFSe());
        params.put("prefeituraFone", "(48)3431-0074");
        params.put("prefeituraEmail", "tributos@criciuma.sc.gov.br");

        String dCompet = nf.getInfNFSe().getDPS().getInfDPS().getDCompet();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dCompet = LocalDate.parse(dCompet).format(formatter);
        params.put("dCompet", dCompet);

        params.put("nDPS", nf.getInfNFSe().getDPS().getInfDPS().getNDPS());
        params.put("serie", nf.getInfNFSe().getDPS().getInfDPS().getSerie());

        String dhEmi = nf.getInfNFSe().getDPS().getInfDPS().getDhEmi();
        OffsetDateTime odt = OffsetDateTime.parse(dhEmi);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dhEmi = odt.format(formatter);
        params.put("dhEmi", dhEmi);

        /////// EMITENTE
        params.put("emitCNPJ", FunctionCustom.mascara(nf.getInfNFSe().getEmit().getCNPJ(),"##.###.###/####-##"));
        params.put("emitxNome", nf.getInfNFSe().getEmit().getXNome());
        params.put("emitxLgr", nf.getInfNFSe().getEmit().getEnderNac().getXLgr());
        params.put("emitnro", FunctionCustom.nvl(nf.getInfNFSe().getEmit().getEnderNac().getNro(),"SN"));
        params.put("emitxBairro", nf.getInfNFSe().getEmit().getEnderNac().getXBairro());
        params.put("emitemail", nf.getInfNFSe().getEmit().getEmail());
        params.put("emitfone", FunctionCustom.mascara(nf.getInfNFSe().getEmit().getFone(),"(##) #####-#####"));
        params.put("emitCEP", FunctionCustom.mascara(nf.getInfNFSe().getEmit().getEnderNac().getCEP(),"#####-###"));
        params.put("opSimpNac", nf.getInfNFSe().getDPS().getInfDPS().getPrest().getRegTrib().getOpSimpNac());
        params.put("regApTribSN", nf.getInfNFSe().getDPS().getInfDPS().getPrest().getRegTrib().getRegApTribSN());
        MunicipioModel munEmitente = municipioService.getMunicipio(Long.parseLong(FunctionCustom.nvl(nf.getInfNFSe().getEmit().getEnderNac().getCMun(),"0")));
        params.put("emitxMun", munEmitente.getNome());
        params.put("emitUF", FunctionCustom.nvl(nf.getInfNFSe().getEmit().getEnderNac().getUF(), munEmitente.getUf()));

        /////// TOMADOR
        params.put("tomaCNPJ", FunctionCustom.mascara(nf.getInfNFSe().getDPS().getInfDPS().getTomad().getCnpj(),"##.###.###/####-##"));
        params.put("tomaCPF", FunctionCustom.mascara(nf.getInfNFSe().getDPS().getInfDPS().getTomad().getCpf(),"###.###.###-##"));
        params.put("tomaxNome", nf.getInfNFSe().getDPS().getInfDPS().getTomad().getXNome());
        params.put("tomafone", FunctionCustom.mascara(nf.getInfNFSe().getDPS().getInfDPS().getTomad().getFone(),"(##) #####-#####"));
        params.put("tomaIM", nf.getInfNFSe().getDPS().getInfDPS().getTomad().getIm());
        params.put("tomaemail", nf.getInfNFSe().getDPS().getInfDPS().getTomad().getEmail());
        MunicipioModel munToma = municipioService.getMunicipio(Long.parseLong(FunctionCustom.nvl(nf.getInfNFSe().getDPS().getInfDPS().getTomad().getEnd().getEndNac().getCMun(),"0")));
        params.put("tomaxMun", munToma.getNome());
        params.put("tomaUf", munToma.getUf());

        params.put("tomaCEP", FunctionCustom.mascara(nf.getInfNFSe().getDPS().getInfDPS().getTomad().getEnd().getEndNac().getCEP(),"#####-###"));
        params.put("tomaxLgr", nf.getInfNFSe().getDPS().getInfDPS().getTomad().getEnd().getXLgr());
        params.put("tomanro", FunctionCustom.nvl(nf.getInfNFSe().getDPS().getInfDPS().getTomad().getEnd().getNro(),""));
        params.put("tomaxBairro", nf.getInfNFSe().getDPS().getInfDPS().getTomad().getEnd().getXBairro());

        /////// SERVICO
        params.put("xTribNac", nf.getInfNFSe().getXTribNac());
        params.put("cTribNac", nf.getInfNFSe().getDPS().getInfDPS().getServ().getCServ().getCTribNac());
        MunicipioModel munLocPrestacao = municipioService.getMunicipio(Long.parseLong(FunctionCustom.nvl(nf.getInfNFSe().getDPS().getInfDPS().getServ().getLocPrest().getCLocPrestacao(),"0")));
        params.put("xLocPrestacao", FunctionCustom.nvl(munLocPrestacao.getNome(), nf.getInfNFSe().getXLocPrestacao()));
        params.put("ufLocPrestacao", FunctionCustom.nvl(munLocPrestacao.getUf(), ""));
        params.put("xDescServ", nf.getInfNFSe().getDPS().getInfDPS().getServ().getCServ().getXDescServ());
        params.put("cTribMun", nf.getInfNFSe().getDPS().getInfDPS().getServ().getCServ().getCTribMun());


        /////// TRIBUTACAO MUNICIPAL
        params.put("tribISSQN", nf.getInfNFSe().getDPS().getInfDPS().getValores().getTrib().getTribMun().getTribISSQN());
        params.put("regEspTrib", nf.getInfNFSe().getDPS().getInfDPS().getPrest().getRegTrib().getRegEspTrib());
        MunicipioModel munLocIndidencia = municipioService.getMunicipio(Long.parseLong(FunctionCustom.nvl(nf.getInfNFSe().getCLocIncid(),"0")));
        params.put("xLocIncid", FunctionCustom.nvl(munLocIndidencia.getNome(), nf.getInfNFSe().getXLocIncid()));
        params.put("tpRetISSQN", nf.getInfNFSe().getDPS().getInfDPS().getValores().getTrib().getTribMun().getTpRetISSQN());
        params.put("vServ", nf.getInfNFSe().getDPS().getInfDPS().getValores().getVServPrest().getVServ());
        params.put("vLiq", nf.getInfNFSe().getValores().getVLiq());

        String vDescCond="", vDescIncond="";
        try {
            vDescCond = FunctionCustom.nvl(nf.getInfNFSe().getDPS().getInfDPS().getValores().getVDescCondIncond().getVDescCond(), "");
        }catch (Exception e){}
        finally {
            params.put("vDescCond", vDescCond);
        }

        try {
            vDescIncond = FunctionCustom.nvl(nf.getInfNFSe().getDPS().getInfDPS().getValores().getVDescCondIncond().getVDescIncond(), "");
        }catch (Exception e){}
        finally {
            params.put("vDescIncond", vDescIncond);
        }


        try{params.put("vTotTribFed", nf.getInfNFSe().getDPS().getInfDPS().getValores().getTrib().getTotTrib().getVtotTrib().getVTotTribFed());} catch (Exception e){};
        try{params.put("vTotTribEst", nf.getInfNFSe().getDPS().getInfDPS().getValores().getTrib().getTotTrib().getVtotTrib().getVTotTribEst());} catch (Exception e){};
        try{params.put("vTotTribMun", nf.getInfNFSe().getDPS().getInfDPS().getValores().getTrib().getTotTrib().getVtotTrib().getVTotTribMun());} catch (Exception e){};

        params.put("xNBS", nf.getInfNFSe().getXNBS());
        params.put("cNBS", nf.getInfNFSe().getDPS().getInfDPS().getServ().getCServ().getCNBS());

        JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(print);

    }

}
