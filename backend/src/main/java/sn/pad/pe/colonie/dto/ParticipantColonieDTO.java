package sn.pad.pe.colonie.dto;

import java.util.Date;

import sn.pad.pe.colonie.bo.DossierColonie;
import sn.pad.pe.colonie.bo.GroupeSanguin;
import sn.pad.pe.colonie.bo.Sexe;

public class ParticipantColonieDTO {
    private Long id;
    private DossierColonie codeDossier;
    private String nomEnfant;
    private String prenomEnfant;
    private Date dateNaissance;
    private String lieuNaissance;
    private GroupeSanguin groupeSanguin;
    private Sexe sexe;
    private String matriculeParent;
    private String nomParent;
    private String prenomParent;
    private String status;
    private String matriculeAgent;
    private String nomAgent;
    private String prenomAgent;
    private String ficheSocial; 
    private String document; 

    private byte[] ficheSocialBytes; 
    private byte[] documentBytes; 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DossierColonie getCodeDossier() {
        return codeDossier;
    }
    public void setCodeDossier(DossierColonie dossierColonie) {
        this.codeDossier = dossierColonie;
    }
    public String getNomEnfant() {
        return nomEnfant;
    }
    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }
    public String getPrenomEnfant() {
        return prenomEnfant;
    }
    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getLieuNaissance() {
        return lieuNaissance;
    }
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
    public GroupeSanguin getGroupeSanguin() {
        return groupeSanguin;
    }
    public void setGroupeSanguin(GroupeSanguin groupeSanguin2) {
        this.groupeSanguin = groupeSanguin2;
    }
    public Sexe getSexe() {
        return sexe;
    }
    public void setSexe(Sexe sexe2) {
        this.sexe = sexe2;
    }
    public String getMatriculeParent() {
        return matriculeParent;
    }
    public void setMatriculeParent(String matricule) {
        this.matriculeParent = matricule;
    }
    public String getNomParent() {
        return nomParent;
    }
    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }
    public String getPrenomParent() {
        return prenomParent;
    }
    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMatriculeAgent() {
        return matriculeAgent;
    }
    public void setMatriculeAgent(String matriculeAgent) {
        this.matriculeAgent = matriculeAgent;
    }
    public String getNomAgent() {
        return nomAgent;
    }
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }
    public String getPrenomAgent() {
        return prenomAgent;
    }
    public void setPrenomAgent(String prenomAgent) {
        this.prenomAgent = prenomAgent;
    }
    public String getFicheSocial() {
        return ficheSocial;
    }
    public void setFicheSocial(String ficheSocial) {
        this.ficheSocial = ficheSocial;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }

    public byte[] getFicheSocialBytes() {
        return ficheSocialBytes;
    }

    public void setFicheSocialBytes(byte[] ficheSocialBytes) {
        this.ficheSocialBytes = ficheSocialBytes;
    }

    public byte[] getDocumentBytes() {
        return documentBytes;
    }

    public void setDocumentBytes(byte[] documentBytes) {
        this.documentBytes = documentBytes;
    }

}