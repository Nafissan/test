import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Satisfaction } from '../model/satisfaction.model'
import { Reponse } from '../model/reponse.model';

@Injectable({
    providedIn: 'root',
  })
  export class ReponseService {
    private url = '/pss-backend/reponses';

    private httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
  
    constructor(private httpClient: HttpClient) {}

    getAllReponses(satisfaction: Satisfaction): Observable<HttpResponse<any>> {
        const httpOptions = {
            headers: this.httpOptions.headers,
            body:satisfaction,
          };
          return this.httpClient.get<any>(this.url, httpOptions)
          .pipe(
            catchError(this.errorHandler));
      }
    
      addReponse(reponse: Reponse): Observable<HttpResponse<any>>{
        return this.httpClient.post<any>(this.url, JSON.stringify(reponse),{observe : 'response', headers: this.httpOptions.headers})
        .pipe(catchError(this.errorHandler));
      }

      updateReponse(reponse: Reponse): Observable<HttpResponse<any>>{
        return this.httpClient.post<any>(this.url, JSON.stringify(reponse),{observe : 'response', headers: this.httpOptions.headers})
        .pipe(catchError(this.errorHandler));
      }
      // Gérer les erreurs HTTP
      private errorHandler(error: any) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
          // Erreur côté client
          errorMessage = error.error.message;
        } else {
          // Erreur côté serveur
          errorMessage = `Code d'erreur: ${error.status}\nMessage: ${error.message}`;
        }
        console.error(errorMessage);
        return throwError(errorMessage);
      }
  }