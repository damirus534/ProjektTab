import { ProductInfoAdmin } from "../product-info/ProductInfoAdmin";

export class ProductAdmin {

  constructor(
    public id: number | null,
    public productInfo: ProductInfoAdmin | null,
    public size: string = '',
    public amountAvailable: number = 0
  ) {}

}
