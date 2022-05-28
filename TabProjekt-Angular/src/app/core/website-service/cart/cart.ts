import { ProductAdmin } from "../../product/productAdmin";
import { User } from "../../user/user";

export class Cart {

    constructor(
        public id: number = 0,
        public amount: number = 0,
        public user: User | null = null,
        public product: ProductAdmin | null = null
    ) {}
}
