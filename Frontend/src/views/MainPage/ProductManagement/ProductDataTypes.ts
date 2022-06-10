export interface productData {
    "id": number,
    "name": string,
    "inventory": number,
    "discount": number,
    "price": number,
    "monthly": number,
    "merchantId": number,
    // "createAt": string,
    // "updateAt": string,
}

export type productDataInserted = Omit<productData,'id'>