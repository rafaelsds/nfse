package com.apoia.nfse.models;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "NFSe", namespace = "http://www.sped.fazenda.gov.br/nfse")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class NFSeXml {

    @XmlAttribute(name = "versao")
    private String versao;

    @XmlElement(name = "infNFSe")
    private InfNFSe infNFSe;


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InfNFSe {

        @XmlAttribute(name = "Id")
        private String id;

        @XmlElement(name = "xLocEmi")
        private String xLocEmi;

        @XmlElement(name = "xLocPrestacao")
        private String xLocPrestacao;

        @XmlElement(name = "nNFSe")
        private String nNFSe;

        @XmlElement(name = "cLocIncid")
        private String cLocIncid;

        @XmlElement(name = "xLocIncid")
        private String xLocIncid;

        @XmlElement(name = "xTribNac")
        private String xTribNac;

        @XmlElement(name = "xNBS")
        private String xNBS;

        @XmlElement(name = "verAplic")
        private String verAplic;

        @XmlElement(name = "ambGer")
        private String ambGer;

        @XmlElement(name = "tpEmis")
        private String tpEmis;

        @XmlElement(name = "procEmi")
        private String procEmi;

        @XmlElement(name = "cStat")
        private String cStat;

        @XmlElement(name = "dhProc")
        private String dhProc;

        @XmlElement(name = "nDFSe")
        private String nDFSe;

        // GRUPOS COMPLEXOS
        @XmlElement(name = "emit")
        private Emit emit;

        @XmlElement(name = "valores")
        private ValoresNfe valores;

        @XmlElement(name = "DPS")
        private DPS DPS;

    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ValoresNfe {
        @XmlElement(name = "vLiq")
        private String vLiq;
    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Emit {
        @XmlElement(name = "CNPJ")
        private String CNPJ;

        @XmlElement(name = "xNome")
        private String xNome;

        @XmlElement(name = "enderNac")
        private EnderNac enderNac;

        @XmlElement(name = "fone")
        private String fone;

        @XmlElement(name = "email")
        private String email;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EnderNac {
        @XmlElement(name = "xLgr")
        private String xLgr;

        @XmlElement(name = "nro")
        private String nro;

        @XmlElement(name = "xBairro")
        private String xBairro;

        @XmlElement(name = "cMun")
        private String cMun;

        @XmlElement(name = "UF")
        private String UF;

        @XmlElement(name = "CEP")
        private String CEP;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DPS {
        @XmlElement(name = "infDPS")
        private InfDPS infDPS;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class InfDPS {

        @XmlAttribute(name = "Id")
        private String id;

        @XmlElement(name = "tpAmb")
        private String tpAmb;

        @XmlElement(name = "dhEmi")
        private String dhEmi;

        @XmlElement(name = "verAplic")
        private String verAplic;

        @XmlElement(name = "serie")
        private String serie;

        @XmlElement(name = "nDPS")
        private String nDPS;

        @XmlElement(name = "dCompet")
        private String dCompet;

        @XmlElement(name = "tpEmit")
        private String tpEmit;

        @XmlElement(name = "cLocEmi")
        private String cLocEmi;


        // GRUPOS COMPLEXOS

        @XmlElement(name = "prest")
        private Prest prest;

        @XmlElement(name = "toma")
        private Tomad tomad;

        @XmlElement(name = "serv")
        private Serv serv;

        @XmlElement(name = "valores")
        private Valores valores;
    }



    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RegTrib {
        @XmlElement(name = "opSimpNac")
        private String opSimpNac;

        @XmlElement(name = "regApTribSN")
        private String regApTribSN;

        @XmlElement(name = "regEspTrib")
        private String regEspTrib;
    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Prest {

        @XmlElement(name = "CNPJ")
        private String cnpj;

        @XmlElement(name = "CPF")
        private String cpf;

        @XmlElement(name = "NIF")
        private String nif;

        @XmlElement(name = "cNaoNIF")
        private String cNaoNIF;

        @XmlElement(name = "IM")
        private String im;

        @XmlElement(name = "xNome")
        private String xNome;

        @XmlElement(name = "end")
        private End end;

        @XmlElement(name = "fone")
        private String fone;

        @XmlElement(name = "email")
        private String email;

        @XmlElement(name = "regTrib")
        private RegTrib regTrib;

    }

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class End {
        @XmlElement(name = "endNac")
        private EndNac endNac;

        @XmlElement(name = "xLgr")
        private String xLgr;

        @XmlElement(name = "nro")
        private String nro;

        @XmlElement(name = "xCpl")
        private String xCpl;

        @XmlElement(name = "xBairro")
        private String xBairro;

    }


    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EndNac {
        @XmlElement(name = "cMun")
        private String cMun;

        @XmlElement(name = "CEP")
        private String CEP;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Tomad {

        @XmlElement(name = "CNPJ")
        private String cnpj;

        @XmlElement(name = "CPF")
        private String cpf;

        @XmlElement(name = "NIF")
        private String nif;

        @XmlElement(name = "cNaoNIF")
        private String cNaoNIF;

        @XmlElement(name = "IM")
        private String im;

        @XmlElement(name = "xNome")
        private String xNome;

        @XmlElement(name = "end")
        private End end;

        @XmlElement(name = "fone")
        private String fone;

        @XmlElement(name = "email")
        private String email;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class Serv {
        @XmlElement(name = "locPrest")
        private LocPrest locPrest;

        @XmlElement(name = "cServ")
        private Cserv cServ;

        @XmlElement(name = "infoCompl")
        private InfoCompl infoCompl;

    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class LocPrest {

        @XmlElement(name = "cLocPrestacao")
        private String cLocPrestacao;

        @XmlElement(name = "cPaisPrestacao")
        private String cPaisPrestacao;
    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class Cserv {

        @XmlElement(name = "cTribNac")
        private String cTribNac;

        @XmlElement(name = "cTribMun")
        private String cTribMun;

        @XmlElement(name = "xDescServ")
        private String xDescServ;

        @XmlElement(name = "cNBS")
        private String cNBS;

        @XmlElement(name = "cIntContrib")
        private String cIntContrib;

    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class InfoCompl {

        @XmlElement(name = "idDocTec")
        private String idDocTec;

        @XmlElement(name = "docRef")
        private String docRef;

        @XmlElement(name = "xInfComp")
        private String xInfComp;
    }



    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Valores {

        @XmlElement(name = "vServPrest")
        private VServPrest vServPrest;

        @XmlElement(name = "vDescCondIncond")
        private VdescCondIncond vDescCondIncond;

        @XmlElement(name = "trib")
        private Trib trib;

    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VServPrest {
        @XmlElement(name = "vReceb")
        private String vReceb;

        @XmlElement(name = "vServ")
        private String vServ;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VdescCondIncond {
        @XmlElement(name = "vDescIncond")
        private Number vDescIncond;

        @XmlElement(name = "vDescCond")
        private Number vDescCond;
    }


    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Trib {
        @XmlElement(name = "tribMun")
        private TribMun tribMun;

        @XmlElement(name = "totTrib")
        private TotTrib totTrib;
    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TribMun {
        @XmlElement(name = "tribISSQN")
        private String tribISSQN;

        @XmlElement(name = "cPaisResult")
        private String cPaisResult;

        @XmlElement(name = "tpImunidade")
        private String tpImunidade;

        @XmlElement(name = "tpRetISSQN")
        private String tpRetISSQN;
    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TotTrib {
        @XmlElement(name = "vTotTrib")
        private VtotTrib VtotTrib;

    }

    @Data
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VtotTrib {
        @XmlElement(name = "vTotTribFed")
        private String vTotTribFed;

        @XmlElement(name = "vTotTribEst")
        private String vTotTribEst;

        @XmlElement(name = "vTotTribMun")
        private String vTotTribMun;
    }

}