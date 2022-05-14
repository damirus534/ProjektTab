import { ProductInfo } from "../product-info/product-info";

export class Photo {

    constructor(
        public id: number | null,
        public productInfo: ProductInfo | null,
        public photoUrl: string = ''
    ) {}

}
