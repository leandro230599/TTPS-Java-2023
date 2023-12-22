

export class Usuario {
    
    private usuario_id: number;
    private first_name: string;
    private last_name: string;
    private email: string;
    private password: string;
    private username: string;

    public setUsuarioId(value:number){
        this.usuario_id = value;
    }
    public getUsuarioId():number {
        return this.usuario_id;
    }
    public getFirstName(): string {
        return this.first_name;
    }

    public setFirstName(value: string) {
        this.first_name = value;
    }
    public getLastName(): string {
        return this.last_name;
    }
    public setLastName(value: string) {
        this.last_name = value;
    }

    public getEmail(): string {
        return this.email;
    }
    public setEmail(value: string) {
        this.email = value;
    }

    public getPassword(): string {
        return this.password;
    }

    public setPassword(value: string) {
        this.password = value;
    }
    public setUsername(value:string){
        this.username = value;
    }
    public getUsername(){
        return this.username;
    }

}
