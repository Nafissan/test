package sn.pad.pe.colonie.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import sn.pad.pe.colonie.dto.RapportProspectionDTO;
import sn.pad.pe.colonie.services.RapportProspectionService;
import sn.pad.pe.configurations.exception.Message;

@RestController
@Api(value = "API pour l'entité rapport de prospection")
public class RapportProspectionController {

    @Autowired
    private RapportProspectionService rapportProspectionService;

    Message message;

    @ApiOperation(value = "Liste des rapports de prospection", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Liste récupérée avec succès"),
            @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
            @ApiResponse(code = 403, message = "L'accès à la ressource que vous tentez d'atteindre est interdit"),
            @ApiResponse(code = 404, message = "La ressource que vous tentez d'atteindre est introuvable.") })
    @GetMapping("/rapportsProspection")
    public List<RapportProspectionDTO> getAllRapportsProspection() {
        return rapportProspectionService.getAllRapportsProspection();
    }

    @ApiOperation(value = "Récupération d'un rapport par ID", response = RapportProspectionDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Objet récupéré avec succès"),
    @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
    @ApiResponse(code = 403, message = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
    @ApiResponse(code = 404, message = "La ressource que vous tentiez d'atteindre est introuvable.") })
    @GetMapping("/rapportsProspection/{id}")
    public ResponseEntity<RapportProspectionDTO> getRapportProspectionById(@PathVariable Long id) {
        return ResponseEntity.ok().body(rapportProspectionService.getRapportProspectionById(id));
    }

    @ApiOperation(value = "Récupération d'un rapport par code dossier", response = RapportProspectionDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Objet récupéré avec succès"),
            @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
            @ApiResponse(code = 403, message = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
            @ApiResponse(code = 404, message = "La ressource que vous tentiez d'atteindre est introuvable.") })
    @GetMapping("/rapportsProspection/codeDossier/{code}")
    public ResponseEntity<RapportProspectionDTO> getRapportByCodeDossier(@PathVariable String code) {
        return ResponseEntity.ok().body(rapportProspectionService.getRapportByCodeDossier(code));
    }

    @ApiOperation(value = "Création d'un rapport de prospection", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Objet créé avec succès"),
    @ApiResponse(code = 409, message = "La ressource existe déjà"),
    @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
    @ApiResponse(code = 403, message = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
    @ApiResponse(code = 404, message = "La ressource que vous tentiez d'atteindre est introuvable.") })
    @PostMapping("/rapportsProspection")
    public ResponseEntity<RapportProspectionDTO> saveRapportProspection(@RequestBody RapportProspectionDTO rapportProspectionDTO) {
        return ResponseEntity.ok().body(rapportProspectionService.saveRapportProspection(rapportProspectionDTO));
    }

    @ApiOperation(value = "Mise à jour d'un rapport de prospection", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Objet modifié avec succès"),
    @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
    @ApiResponse(code = 403, message = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
    @ApiResponse(code = 404, message = "La ressource que vous tentiez d'atteindre est introuvable.") })
    @PutMapping("/rapportsProspection")
    public ResponseEntity<Message> updateRapportProspection(@RequestBody RapportProspectionDTO rapportProspectionDTO) {
        if (rapportProspectionService.updateRapportProspection(rapportProspectionDTO)) {
            message = new Message(new Date(), "RapportProspection with id " + rapportProspectionDTO.getId() + " updated.", "uri=/rapportsProspection/" + rapportProspectionDTO.getId());
            return ResponseEntity.ok().body(message);
        }
        message = new Message(new Date(), "RapportProspection with id " + rapportProspectionDTO.getId() + " not found.", "uri=/rapportsProspection/" + rapportProspectionDTO.getId());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Suppression d'un rapport de prospection", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Objet supprimé avec succès"),
    @ApiResponse(code = 401, message = "Vous n'êtes pas autorisé à voir la ressource"),
    @ApiResponse(code = 403, message = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
    @ApiResponse(code = 404, message = "La ressource que vous tentiez d'atteindre est introuvable.") })
    @DeleteMapping("/rapportsProspection/{id}")
    public ResponseEntity<Message> deleteRapportProspection(@PathVariable Long id) {
        if (rapportProspectionService.deleteRapportProspection(id)) {
            message = new Message(new Date(), "RapportProspection with id " + id + " deleted.", "uri=/rapportsProspection/" + id);
            return ResponseEntity.ok().body(message);
        }
        message = new Message(new Date(), "RapportProspection with id " + id + " not found.", "uri=/rapportsProspection/" + id);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}