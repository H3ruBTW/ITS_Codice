import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ProdottoStruct, ProductsResponse } from "../modelli/product.model";

@Injectable({
    providedIn: "root"
})
export class ApiCall {
    http = inject(HttpClient)

    getAllProducts(){
        return this.http.get<ProductsResponse>("https://dummyjson.com/products")
    }

    getProductById(id:number){
        return this.http.get<ProdottoStruct>("https://dummyjson.com/products/" + id)
    }
}