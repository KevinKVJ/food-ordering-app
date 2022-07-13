import { css } from '@emotion/react';
import SvgIcon from '@/components/SvgIcon';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import { useMemo } from 'react';
import Logo from '@/views/common/Logo';
import DeliveryDropDown from './Delivery_Dropdown';
import AddressDType_DropDown from './Address_DeliveryType_Dropdown';
import ShoppingCart_Button from './ShoppingCart_Button';
import User from './User';
import SearchBar from './SearchBar'

export default () => {
    const navStyles = useMemo(
        () =>
            css`
                height: 70px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                outline: 1px solid;

                padding: 0 20px;
            `,
        []
    );

    return (
        <div css={[navStyles]}>
            <FlexLayout className='navbar-left'>
                <SvgIcon name='menu' width={30} height={30} />
                <Logo></Logo>
                <DeliveryDropDown />
                <AddressDType_DropDown />
            </FlexLayout>
            <SearchBar></SearchBar>
            <FlexLayout className='navbar-right'>
                <ShoppingCart_Button />
                <User />
            </FlexLayout>
        </div>
    );
};
