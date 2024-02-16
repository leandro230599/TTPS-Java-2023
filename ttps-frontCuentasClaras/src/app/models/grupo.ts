

export class Grupo {
    
    private grupo_id: number;
    private nombre: string;
    private categoria_id: string;

    public setGrupoId(value:number){
        this.grupo_id = value;
    }
    public getGrupoId():number {
        return this.grupo_id;
    }
    public getNombre(): string {
        return this.nombre;
    }

    public setNombre(value: string) {
        this.nombre = value;
    }
    public setCategoriaId(value:string){
        this.categoria_id = value;
    }
    public getCategoriaId():string {
        return this.categoria_id;
    }
}
