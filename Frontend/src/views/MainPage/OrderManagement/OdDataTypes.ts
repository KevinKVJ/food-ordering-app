export interface orderData {
    address?: string;
    /**
     * 买家ID
     */
    clientId?: number;
    /**
     * 商品评价
     */
    comment?:  string;
    createAt?: string;
    /**
     * 配送费
     */
    deliveryFee?: number;
    /**
     * 配送方式ID外键
     */
    deliveryMethodId?: number;
    /**
     * 送达时间
     */
    deliveryTime?: string;
    id?:           number;
    /**
     * 商家ID
     */
    merchantId?: number;
    /**
     * 支付方式 整数枚举值
     */
    paymentMethod?: number;
    phone?:         string;
    /**
     * 配送员开始送货的时间
     */
    shipmentTime?: string;
    /**
     * 订单状态
     */
    statusId?: number;
    /**
     * 总价格
     */
    totalPrice?: number;
    updateAt?:   string;
}

interface orderProductData {
    name?: string,
    options?: string,
    price?: string,
    count?: string
}