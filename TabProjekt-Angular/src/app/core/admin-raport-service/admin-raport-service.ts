export class AdminRaportService {
  public orderHistoryDate: string | null;
  public productName: string | null;
  public orderAmount: number | null;
  public orderSellPrice: number | null;
  public productSize: string | null;
  public buyingPrice: number | null;
  public categoryName: string | null;

  constructor(orderHistoryDate:string, productName:string, orderAmount: number, orderSellPrice: number, productSize: string, buyingPrice:number, categoryName:string) {
    this.orderHistoryDate = orderHistoryDate;
    this.productName = productName;
    this.orderAmount = orderAmount;
    this.orderSellPrice=orderSellPrice;
    this.productSize = productSize;
    this.buyingPrice = buyingPrice;
    this.categoryName = categoryName;
  }
}
