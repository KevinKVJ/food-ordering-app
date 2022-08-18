import { css } from '@emotion/react';
import { useMemo, useState } from 'react';

import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import Dropdown from '@/components/Dropdown/Dropdown_CM';

const DeliveryDD = () => {
    const [dropdownActive, setDropdownActive] = useState(false);

    const deliveryDDTitle = css`
        font-weight: 600;

        cursor: pointer;
        user-select: none;
    `;

    return (
        <>
            <div
                className='delivery_DD_wrapper'
                onClick={e => {
                    e.nativeEvent.stopImmediatePropagation();
                    setDropdownActive(!dropdownActive);
                }}
            >
                <FlexLayout className='Delivery_DD' spacing={5} css={deliveryDDTitle}>
                    <span>Delivery</span>
                    <SvgIcon name='dropdown' width={10} height={10} />
                </FlexLayout>
            </div>
            <Dropdown
                activeSwitch={dropdownActive}
                onClose={() => setDropdownActive(false)}
            ></Dropdown>
        </>
    );
};
export default DeliveryDD;
