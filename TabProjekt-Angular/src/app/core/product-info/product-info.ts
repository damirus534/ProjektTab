export class ProductInfo {
    
    constructor(
        public id: number = 0,
        public categoryId: number = 0,
        public productName: string = '',
        public description: string = '',
        public buyingPrice: number = 0,
        public sellingPrice: number = 0
    ) {}

}
