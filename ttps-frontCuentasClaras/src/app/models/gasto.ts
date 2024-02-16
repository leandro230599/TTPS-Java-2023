export class Gasto {
    
    private idCat: number;
    private fecha: string;
    private monto:number;
    private idFD:number;
    private fdV:number = 0;
    private idGrupoPersona:number;

    public setIdCat(value:number){
        this.idCat = value;
    }
    public getIdCat(): number{
        return this.idCat;
    }

    public setFecha(value:string){
        this.fecha=value;
    }
    public getFecha():string{
        return this.fecha;
    }

    public setMonto(value:number){
        this.monto = value;
    }
    public getMonto():number{
        return this.monto;
    }

    public setidFD(value:number){
        this.idFD = value;
    }
    public getidFD():number{
        return this.idFD;
    }

    public setfdV(value:number){
        this.fdV = value;
    }
    public getfdV():number{
        return this.fdV;
    }

    public setidGrupoPersona(value:number){
        this.idGrupoPersona = value;
    }
    public getidGrupoPersona():number{
        return this.idGrupoPersona;
    }
}
