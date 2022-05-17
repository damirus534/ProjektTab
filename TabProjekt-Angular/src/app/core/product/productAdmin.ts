import { ProductInfo } from "../product-info/product-info";

export class ProductAdmin {

  constructor(
    public id: number | null,
    public productInfo: ProductInfo | null,
    public size: string = '',
    public amountAvailable: number = 0
  ) {}

}
