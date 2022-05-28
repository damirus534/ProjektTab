export class OrderHistory {
  constructor(orderHistoryDate:string,productName:string,orderAmount:number,orderSellPrice:number,productSize:string) {
    this.orderHistoryDate = orderHistoryDate;
    this.productName = productName;
    this.orderAmount = orderAmount;
    this.orderSellPrice = orderSellPrice;
    this.productSize = productSize;

  }
  public orderHistoryDate:string|null
  public productName:string
  public orderAmount:number|null
  public orderSellPrice:number
  public productSize:string
}
