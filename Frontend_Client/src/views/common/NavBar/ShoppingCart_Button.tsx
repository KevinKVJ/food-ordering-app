import SvgIcon from '@/components/SvgIcon';
import { css } from '@emotion/react';
import { useMemo } from 'react';
import FlexLayout from '@/components/FlexLayout/FlexLayout';

const ShoppingCartButton = () => {
    const scButtonStyles = useMemo(
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
        `,
        []
    );
    return <div css={scButtonStyles}>
        <SvgIcon name='shoppingcart' width={20} height={20} fill="#fff"/>
        <span style={{color:"#fff"}}>1</span>
    </div>;
};
export default ShoppingCartButton;
