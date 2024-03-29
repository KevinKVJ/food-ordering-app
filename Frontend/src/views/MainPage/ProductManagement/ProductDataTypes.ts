export interface productData {
    id: number,
    name: string,
    inventory: number,
    discount: number,
    price: number,
    monthly: number,
    merchantId: number,
    Categories?: string[]
    // "createAt": string,
    // "updateAt": string,
}

export type productDataInserted = Omit<productData, 'id'>

export interface productCategory {
    id: number,
    name: string,
}