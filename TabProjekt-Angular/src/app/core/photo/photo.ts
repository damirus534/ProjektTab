import { ProductInfoAdmin } from "../product-info/ProductInfoAdmin";

export class Photo {

    constructor(
        public id: number | null,
        public productInfo: ProductInfoAdmin | null,
        public photoUrl: string = ''
    ) {}

}
