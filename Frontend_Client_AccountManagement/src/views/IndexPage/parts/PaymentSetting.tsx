import { css } from '@emotion/react';

import Flex from '@/components/FlexLayout/FlexLayout';

import AccountSettingBlock from './AccountSettingBlock';

const paymentStyle = css`
    .payment_content {
        .subtitle {
            font-size: 14px;
            font-weight: 600;

            &.larger {
                font-size: 18px;
            }
            &.smaller {
                font-size: 13px;
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
                            <div className='subtitle smaller'>Saved Payment Method</div>
                        </div>
                        <div className='add_new'>
                            <div className='subtitle smaller'>Add New Payment Method</div>
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
