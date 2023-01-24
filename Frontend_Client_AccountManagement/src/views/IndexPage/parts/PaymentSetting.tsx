import { css } from '@emotion/react';

import Flex from '@/components/FlexLayout/FlexLayout';

import AccountSettingBlock from './AccountSettingBlock';

const paymentStyle = css`
    .payment_content {
        display: flex;

        .methods,
        .cash {
            flex-grow: 1;
            flex-basis: 50%;
            flex-shrink: 1;
        }

        .split_vertical_line {
            border-right: 2px solid;
            margin: 0 20px;
        }

        .cash {
            .cash_prompt {
                color: #a9a9a9;
                font-size: 12px;
                word-break: pre;
            }
        }

        .subtitle {
            font-size: 14px;
            font-weight: 600;

            &.larger {
                font-size: 18px;
            }
        }
    }
`;

const PaymentSetting = ({ className }: { className?: string }) => {
    return (
        <div css={paymentStyle} className={className}>
            <AccountSettingBlock title='Payment'>
                <div className='payment_content'>
                    <div className='methods'>
                        <div className='saved'>
                            <div className='subtitle'>Saved Payment Method</div>
                        </div>
                        <div className='add_new'>
                            <div className='subtitle'>Add New Payment Method</div>
                        </div>
                    </div>
                    <div className='split_vertical_line' />
                    <div className='cash'>
                        <Flex spacing={25} vertical alignItems='flex-start'>
                            <div className='subtitle larger'>Cash</div>
                            <div className='amount'>$0.00</div>
                            <div className='cash_prompt'>
                                Your credits will be automatically applied to your next
                                order
                            </div>
                        </Flex>
                    </div>
                </div>
            </AccountSettingBlock>
        </div>
    );
};

export default PaymentSetting;
