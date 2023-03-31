import { css } from '@emotion/react';

import HrLine from './HrLine';
import HrLineDashed from './HrLineDashed';

const IndexPage = () => {
    const indexPageStyle = css`
        background-color: #f5f3f1;

        > .navbar,
        > .content {
            background-color: #fff;
        }

        > .navbar {
            height: 68px;
            border-bottom: 1px solid;

            display: flex;
            justify-content: space-between;
            align-items: center;
            > div {
                flex: 0 0 auto;
            }
            .back_link {
                display: flex;
                padding: 0 20px;
                .icon {
                    width: 20px;
                    height: 20px;

                    margin-right: 15px;
                    outline: 1px solid;
                }
                .prompt {
                    font-weight: 600;
                }
            }
            .user_info {
                display: flex;
                /* justify-content: baseline; */
                .avatar {
                    border: 1px solid #e9e9ea;
                    border-radius: 1000px;
                    width: 38px;
                    height: 38px;

                    margin-right: 15px;
                }

                .prompts {
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    &_welcome {
                        color: #797d81;
                        font-size: 12px;
                    }
                    &_with_name {
                        font-size: 16px;
                        color: #1f272d;
                        font-weight: 700;
                    }
                }
            }
        }

        > .content {
            min-height: calc(100vh - 70px);

            width: 920px;
            margin: 0 auto;
            padding: 10px 20px 30px;

            .content-title {
                height: 60px;

                display: flex;
                justify-content: center;
                align-items: center;

                font-size: 18px;
                font-weight: 700;
            }

            .sub_title {
                padding: 10px 0 15px;
                font-weight: 700;
            }
            .address {
                &-content {
                    display: flex;
                    align-items: center;
                    column-gap: 20px;
                    > div {
                        flex: 0 0 auto;
                    }
                }
                &-map {
                    width: 180px;
                    background-color: skyblue;
                    height: 110px;
                    border-radius: 8px;
                }

                &-info {
                    display: flex;
                    align-items: flex-start;
                    flex-direction: column;

                    .receiver {
                        font-weight: 700;
                        margin-bottom: 20px;
                    }

                    .addr {
                        font-size: 14px;
                    }
                }
            }

            .payment_method-list {
                /* height: 80px; */
                border-radius: 16px;
                border: 1px solid #e9e9ea;
                padding: 20px;

                display: flex;
                justify-content: space-between;
                align-items: center;

                .card_info {
                    display: flex;
                    align-items: center;
                    column-gap: 15px;
                }

                .card_icon {
                    width: 48px;
                    height: 48px;
                    border-radius: 1000px;
                    outline: 1px solid;
                }

                .card_type {
                    font-weight: 600;
                    margin-bottom: 10px;
                }

                .card_number {
                    font-size: 12px;
                    color: #797d81;
                }

                .list_open {
                    width: 30px;
                    height: 30px;
                    outline: 1px solid;
                }
            }

            .prices_display {
                padding: 10px 0;

                .price_block {
                    padding: 12px 0;

                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }

                .price_title {
                    color: #797d81;
                    font-size: 14px;
                    font-weight: 600;
                }

                .price_total_title {
                    color: #797d81;
                    font-weight: 600;
                }

                .price {
                    font-weight: 700;
                }
            }

            .promo_code {
                &-area {
                    display: flex;
                    width: fit-content;
                    align-items: center;
                    padding: 20px 30px;

                    border-radius: 15px;
                    background: #fff0f1;
                    border: 1px dashed #f43f5e;
                }

                &-icon {
                    margin-right: 15px;
                    width: 30px;
                    height: 30px;
                    border-radius: 1000px;
                }
                &-prompt {
                    color: #f54749;
                }
            }

            .place_order_button {
                padding: 20px 0;

                background-color: #f43f5e;
                border-radius: 1000px;
                text-align: center;

                color: #fff;
                font-weight: 600;

                /* margin: 20px 0; */
            }
        }
    `;
    return (
        <div className='index_wrapper' css={indexPageStyle}>
            {/* <hr /> */}
            <div className='navbar'>
                <div className='left'>
                    <div className='back_link'>
                        <div className='icon'></div>
                        <div className='prompt'>Back to Menu</div>
                    </div>
                </div>
                <div className='right'>
                    <div className='user_info'>
                        <div className='avatar'></div>
                        <div className='prompts'>
                            <div className='prompts_welcome'>Welcome back</div>
                            <div className='prompts_with_name'>Holla, Alex Bruno</div>
                        </div>
                    </div>
                </div>
            </div>
            <div className='content'>
                <div className='content-title'>Checkout</div>
                <HrLine />
                <div className='address'>
                    <div className='address-title sub_title'>Address</div>
                    <div className='address-content'>
                        <div className='address-map'></div>
                        <div className='address-info'>
                            <div className='receiver'>Office</div>
                            <div className='addr'>
                                Adi Sucipto St. No. 23 Solo, <br /> Central Java,
                                Indonesia
                            </div>
                        </div>
                    </div>
                </div>
                <HrLine />
                <div className='payment_method'>
                    <div className='payment_method-title sub_title'>Payment Method</div>
                    <div className='payment_method-list'>
                        <div className='card_info'>
                            <div className='card_icon'></div>
                            <div className='card_text'>
                                <div className='card_type'>MasterCard</div>
                                <div className='card_number'>**** **** 1234</div>
                            </div>
                        </div>
                        <div className='list_open'>
                            <div className='list_open-icon'></div>
                        </div>
                    </div>
                </div>
                <HrLine />
                <div className='prices_display'>
                    <div className='shipping_cost price_block'>
                        <div className='price_title'>Shipping cost</div>
                        <div className='price'>{`$5.31`}</div>
                    </div>
                    <div className='sub_total price_block'>
                        <div className='price_title'>Sub total</div>
                        <div className='price'>{`$16.00`}</div>
                    </div>
                    <HrLineDashed />
                    <div className='total price_block'>
                        <div className='price_total_title'>Total</div>
                        <div className='price'>{`$21.31`}</div>
                    </div>
                </div>
                <HrLine />
                <div className='promo_code'>
                    <div className='promo_code-title sub_title'>
                        Add Voucher or Promo Code
                    </div>
                    <div className='promo_code-area'>
                        <div className='promo_code-icon'></div>
                        <div className='promo_code-prompt'>
                            Add voucher or promo code here
                        </div>
                    </div>
                </div>
                <HrLine />
                <div className='place_order_button'>Place Order</div>
            </div>
        </div>
    );
};

export default IndexPage;
