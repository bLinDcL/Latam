import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import { Persona } from '../model/persona';

@Injectable({
  providedIn: 'root'
})
export class DesafioLatamService {

  url = 'http://localhost:8080/';
  public personas: Persona[] = [];

  constructor( private http: HttpClient ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  crearPersona(persona): Observable<Persona> {
    console.log( persona );
    console.log( this.url );

    return this.http.post<Persona>(this.url + 'desafioLatam/ingresoPersona', JSON.stringify(persona), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  obtenerPersonas(): Observable<Persona[]> {
    console.log( this.url );

    return this.http.get<Persona[]>(this.url + 'desafioLatam/listaPersonas', this.httpOptions)
    .pipe(
      map( (personas: Persona[]) => {
        this.personas = [];
        for ( let persona of personas ) {
          this.personas.push( persona );
        }
        return this.personas;
      } ),
      retry(1),
      catchError(this.handleError)
    );
  }

  handleError( error ) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log( errorMessage );
    return throwError(errorMessage);
 }
}
