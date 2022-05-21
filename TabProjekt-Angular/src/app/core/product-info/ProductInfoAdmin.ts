import { Category } from "../category/category";

export class ProductInfoAdmin {

  constructor(
    public id: number = 0,
    public category: Category = new Category,
    public productName: string = '',
    public description: string = '',
    public buyingPrice: number = 0,
    public sellingPrice: number = 0,
    public isActive: boolean = true
  ) {}

}
