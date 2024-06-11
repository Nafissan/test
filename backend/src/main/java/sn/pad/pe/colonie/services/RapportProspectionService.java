package sn.pad.pe.colonie.services;

import sn.pad.pe.colonie.dto.RapportProspectionDTO;

import java.util.List;

public interface RapportProspectionService {
    RapportProspectionDTO saveRapportProspection(RapportProspectionDTO rapportProspectionDTO);

    RapportProspectionDTO getRapportProspectionById(Long id);

    List<RapportProspectionDTO> getAllRapportsProspection();

    boolean updateRapportProspection(RapportProspectionDTO rapportProspectionDTO);

    boolean deleteRapportProspection(Long id);

     RapportProspectionDTO getRapportByCodeDossier(String code);
}