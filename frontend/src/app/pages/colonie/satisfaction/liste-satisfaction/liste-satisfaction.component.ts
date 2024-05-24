import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ReplaySubject, Observable } from 'rxjs';
import { ListColumn } from 'src/@fury/shared/list/list-column.model';
import { SatisfactionService } from '../../shared/service/satisfaction.service';
import { Satisfaction } from '../../shared/model/satisfaction.model';
import { AddOrUpdateSatisfactionComponent } from '../add-or-update-satisfaction/add-or-update-satisfaction.component';
import { MatDialog } from '@angular/material/dialog';
import { DialogConfirmationService } from 'src/app/shared/services/dialog-confirmation.service';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { NotificationUtil } from 'src/app/shared/util/util';

@Component({
  selector: 'app-liste-satisfaction',
  templateUrl: './liste-satisfaction.component.html',
  styleUrls: ['./liste-satisfaction.component.scss']
})
export class ListeSatisfactionComponent implements OnInit {
  satisfactions: Satisfaction[] = [];
  subject$: ReplaySubject<Satisfaction[]> = new ReplaySubject<Satisfaction[]>(1);
  data$: Observable<Satisfaction[]> = this.subject$.asObservable();
  pageSize = 10;
  dataSource: MatTableDataSource<Satisfaction> | null;
  showProgressBar: boolean = false;

  private paginator: MatPaginator;
  private sort: MatSort;

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
    this.setDataSourceAttributes();
  }
  
  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    this.paginator = mp;
    this.setDataSourceAttributes();
  }

  @Input()
  columns: ListColumn[] = [
    { name: "Checkbox", property: "checkbox", visible: true },
    { name: "Code Dossier Colonie", property: "code", visible: true, isModelProperty: true, },
     { name: "Date de creation", property: "dateCreation", visible: true, isModelProperty: true,},
     { name: "Cree par", property: "traitePar", visible: true,  isModelProperty: true,},
     { name: "Actions", property: "actions", visible: true },
    ] as ListColumn[];
  constructor(private satisfactionService: SatisfactionService,
    private dialog: MatDialog,
    private notificationService: NotificationService,
    private dialogConfirmationService: DialogConfirmationService
  ) {}

  ngOnInit(): void {
    this.getSatisfactions();
  }

  ngAfterViewInit() {
    this.setDataSourceAttributes();
  }

  setDataSourceAttributes() {
    if (this.dataSource) {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
  }

  get visibleColumns() {
    return this.columns.filter((column) => column.visible).map((column) => column.property);
  }

  getSatisfactions() {
    this.satisfactionService.getAllSatisfactions().subscribe((response) => {
      this.satisfactions = response;
      this.subject$.next(this.satisfactions);
      this.showProgressBar = true;
      this.dataSource = new MatTableDataSource<Satisfaction>(this.satisfactions);
    });
  }
  createSatisfaction(){
    this.dialog.open(AddOrUpdateSatisfactionComponent)
    .afterClosed().subscribe((satisfaction: any) => {
  if(satisfaction) {
    this.satisfactions.unshift(satisfaction);
    this.subject$.next(this.satisfactions);
  } 
  });
  }
  updateStaisfaction(satisfaction: Satisfaction){
    this.dialog.open(AddOrUpdateSatisfactionComponent, {
      data: satisfaction,
    })
    .afterClosed()
    .subscribe((satisfaction)=> {
      this.getSatisfactions();
      if(satisfaction){
        const index = this.satisfactions.findIndex(
          (existingsatisfaction) =>
            existingsatisfaction.id === satisfaction.id
        );
        this.satisfactions[index] = satisfaction;
        this.subject$.next(this.satisfactions);
      }
    })
  }
  deleteSatisfaction(satisfaction: Satisfaction) {
    this.dialogConfirmationService.confirmationDialog().subscribe(action => {
      if (action === 'CONFIRMER') {
        this.satisfactionService.deleteSatisfaction(satisfaction.id).subscribe((response) => {
          this.notificationService.success(NotificationUtil.suppression)
          this.satisfactions.splice(
            this.satisfactions.findIndex(
              (existingsatisfaction) => existingsatisfaction.id === satisfaction.id
            ),
            1
          );
          this.subject$.next(this.satisfactions);
        }
        ,err => {
          this.notificationService.warn(NotificationUtil.echec);
        });
      }
    })
  }  
}