export interface ProdottoStruct {
    id: number,
    title: string,
    thumbnail: string
}

export interface ProductsResponse {
    products: ProdottoStruct[];
}