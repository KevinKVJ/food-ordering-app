import { css } from '@emotion/react';
import { Fragment, useMemo, useState } from 'react';

import Dropdown from '@/components/Dropdown/Dropdown';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';

const A_D_DD = () => {
    // const styles = useMemo(
    //     () => css`
    //         display: flex;
    //         gap: 20px;
    //     `,
    //     []
    // );
    const ADDDWrapperStyle = () => css``;
    const [dropdownActive, setDropdownActive] = useState(false);
    return (
        <div css={ADDDWrapperStyle}>
            <button
                onClick={() => {
                    setDropdownActive(!dropdownActive);
                }}>
                open Dropdown Menu
            </button>
            <Dropdown activeSwitch={dropdownActive}></Dropdown>
            <FlexLayout className='Delivery_Type DD' spacing={5}>
                <div>Manhattan, NY</div>
                <div>
                    ASAP <SvgIcon name='dropdown' width={10} height={10} />
                </div>
            </FlexLayout>
        </div>
    );
};
export default A_D_DD;
