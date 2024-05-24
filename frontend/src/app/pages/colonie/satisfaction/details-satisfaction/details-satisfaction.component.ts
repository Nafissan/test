import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Satisfaction } from '../../shared/model/satisfaction.model'; // Assurez-vous d'importer le modèle Satisfaction
import { SatisfactionService } from '../../shared/service/satisfaction.service';
import { MatAccordion } from '@angular/material/expansion';

@Component({
  selector: 'app-details-satisfaction',
  templateUrl: './details-satisfaction.component.html',
  styleUrls: ['./details-satisfaction.component.scss']
})
export class DetailsSatisfactionComponent implements OnInit {
  satisfaction: Satisfaction; // Définissez le type de données de la satisfaction
satisfactionService: SatisfactionService;
@ViewChild(MatAccordion) accordion: MatAccordion;
//Show icon
showIcon = true;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Satisfaction // Injectez les données de satisfaction passées en entrée
  ) { }

  ngOnInit(): void {
    this.satisfactionService.getAllSatisfactions().subscribe(satisfactions => {
      this.satisfaction = satisfactions.find(result => result.code === this.data.code);
    }); // Affectez les données de satisfaction à la propriété du composant
  }
}