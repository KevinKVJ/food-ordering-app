import { css } from '@emotion/react';
import { useMemo, useState } from 'react';

import SvgIcon from '@/components/SvgIcon';
import Drawer from '@/components/Drawer/Drawer';
// import FlexLayout from '@/components/FlexLayout/FlexLayout';

const ShoppingCart = ({ ...props }) => {
    // eslint-disable-next-line no-unused-vars
    const [amount, setAmount] = useState(0);
    const [sideSCState, setSideSCState] = useState(false);

    const shoppingCartStyles = useMemo(
        () => css`
            width: 68px;
            height: 35px;

            display: flex;
            flex-direction: row;
            align-items: center;
            padding: 7px 9px 7px 12px;
            gap: 11px;

            background: #fe8a8a;
            border: 1px solid #e9e9ea;
            border-radius: 1000px;

            cursor: pointer;
        `,
        []
    );
    return (
        <div css={shoppingCartStyles} {...props} onClick={() => setSideSCState(true)}>
            <SvgIcon name='shoppingcart' width={20} height={20} fill='#fff' />
            <span style={{ color: '#fff' }}>{amount}</span>
            <Drawer
                activeSwitch={sideSCState}
                drawerWidth={300}
                onClose={() => {
                    console.log(`lululula`);
                    setSideSCState(false);
                }}
            ></Drawer>
        </div>
    );
};
export default ShoppingCart;
