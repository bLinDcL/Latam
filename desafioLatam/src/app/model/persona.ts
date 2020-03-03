export class Persona {
    public nombre: string;
    public apellido: string;
    public fecha: string;
    public edad: number;
    public mensaje: string;

    contructor ( nombre: string, apellido: string, fecha: string) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
    }
}