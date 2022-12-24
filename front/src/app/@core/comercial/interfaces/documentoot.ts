import { AreasSelect } from './areasSelect';
import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface Documentoot {
    iddocumentoot: number;
    idarea: number;
    fcreate: Date;
    area: string;
    nombre: string;
    descripcion: string;
    ucreate: string;
    idot: number;
    listArea: Array<String>;
    file: File;
    eliminado: boolean;
}

export abstract class DocumentootData {
    abstract list(idot: number): Observable<Documentoot[]>;
    abstract listByIdDocumento(idot: number): Observable<Documentoot[]>;
    abstract listArea(): Observable<AreasSelect[]>;
    abstract get(iddocumentoot: number): Observable<Documentoot>;
    abstract create(documentoot: Documentoot): Observable<Documentoot>;
    abstract update(documentoot: Documentoot): Observable<ResponseMensaje>;
    abstract delete(documentoot: number): Observable<ResponseMensaje>;
}
