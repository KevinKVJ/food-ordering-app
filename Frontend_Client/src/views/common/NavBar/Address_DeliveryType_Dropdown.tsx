import SvgIcon from '@/components/SvgIcon';
import { css } from '@emotion/react';
import { Fragment, useMemo } from 'react';
import FlexLayout from '@/components/FlexLayout/FlexLayout';

const A_D_DD = () => {
    // const styles = useMemo(
    //     () => css`
    //         display: flex;
    //         gap: 20px;
    //     `,
    //     []
    // );
    return (
        <FlexLayout className='Delivery_Type DD' spacing={5}>
            <span>Manhattan, NY</span>
            <span>
                ASAP <SvgIcon name='dropdown' width={10} height={10} />
            </span>
        </FlexLayout>
    );
};
export default A_D_DD;
