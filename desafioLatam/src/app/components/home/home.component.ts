import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { DesafioLatamService } from 'src/app/services/desafio-latam.service';
import { Persona } from 'src/app/model/persona';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  forma: FormGroup;
  cargando = false;

  personaValida: any = {
    nombre: true,
    apellido: true,
    fecha: true
  }

  personas: Persona[] = [];

  persona = new Persona();

  constructor( private _dLatam: DesafioLatamService ) {
    this.forma = new FormGroup({
      'nombre': new FormControl( '' , [ Validators.required, Validators.minLength(3) ]),
      'apellido': new FormControl( '' , [ Validators.required, Validators.minLength(3) ] ),
      'fecha' : new FormControl ('' , Validators.required )
    });

    this._dLatam.obtenerPersonas().subscribe( (resp) => this.personas = resp);
  }

  guardarCambios () {
    console.log(this.cargando);
    this.cargando = true;
    console.log(this.cargando);
    
    // VALIDACIÓN NOMBRE
    if ( !this.forma.controls['nombre'].valid ) {
      this.personaValida['nombre'] = false;
    } else {
      this.personaValida['nombre'] = true;
    }

    // VALIDACIÓN APELLIDO
    if ( !this.forma.controls['apellido'].valid ) {
      this.personaValida['apellido'] = false;
    } else {
      this.personaValida['apellido'] = true;
    }

    // VALIDACIÓN FECHA
    if ( !this.forma.controls['fecha'].valid ) {
      this.personaValida['fecha'] = false;
    } else {
      this.personaValida['fecha'] = true;
    }

    if ( this.forma.valid ) {
      this.persona.nombre = this.forma.controls['nombre'].value;
      this.persona.apellido = this.forma.controls['apellido'].value;
      this.persona.fecha = this.forma.controls['fecha'].value;

      console.log( this.persona );
      
      this._dLatam.crearPersona( this.persona ).subscribe( resp => {
        this.personas.push(resp);
        this.cargando = false;
      } );

      this.forma.reset;
    }
  }

}
