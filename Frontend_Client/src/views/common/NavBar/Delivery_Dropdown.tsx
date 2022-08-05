import { css } from '@emotion/react';
import { useMemo } from 'react';

import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';

const DeliveryDD = () => {
    return (
        <FlexLayout className='Delivery_DD' spacing={5}>
            <span>Delivery</span>
            <SvgIcon name='dropdown' width={10} height={10} />
        </FlexLayout>
    );
};
export default DeliveryDD;
