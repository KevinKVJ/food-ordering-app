export interface orderData {
    address: string;
    /**
     * 买家ID
     */
    clientId:   string;
    clientName: string;
    /**
     * 商品评价
     */
    comment:  string;
    createAt: string;
    /**
     * 配送费
     */
    deliveryFee:    number;
    deliveryMethod: string;
    /**
     * 配送方式ID外键
     */
    deliveryMethodId: string;
    /**
     * 送达时间
     */
    deliveryTime: string;
    /**
     * 过期时间
     */
    due: string;
    id:  string;
    /**
     * 商家ID
     */
    merchantId: string;
    /**
     * 支付方式 整数枚举值
     */
    paymentMethod: number;
    phone:         string;
    products:      orderProductData[];
    /**
     * 配送员开始送货的时间
     */
    shipmentTime:      string;
    statusDescription: string;
    /**
     * 订单状态
     */
    statusId: string;
    /**
     * 总价格
     */
    totalPrice: number;
    updateAt:   string;
}

export interface orderProductData {
    name?: string,
    options?: string,
    price?: string,
    count?: string
}