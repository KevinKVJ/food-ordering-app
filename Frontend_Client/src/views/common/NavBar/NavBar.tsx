import { css } from '@emotion/react';
import { useMemo } from 'react';

import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import Logo from '@/views/common/Logo';

import AddressDTypeDropDown from './Address_DeliveryType_Dropdown';
import DeliveryDropDown from './Delivery_Dropdown';
import SearchBar from './SearchBar';
import ShoppingCartButton from './ShoppingCart_Button';
import User from './User';

const NavBar = () => {
    const wrapper = useMemo(
        () =>
            css`
                border-bottom: 1px solid #e9e9ea;
            `,
        []
    );
    const navStyles = useMemo(
        () =>
            css`
                max-width: 1280px;
                margin: 0 auto;

                height: 70px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                /* outline: 1px solid; */

                padding: 0 20px;
            `,
        []
    );

    return (
        <div css={wrapper}>
            <div css={[navStyles]}>
                <FlexLayout className='navbar-left'>
                    <Logo></Logo>
                    <DeliveryDropDown />
                    <AddressDTypeDropDown />
                </FlexLayout>
                <SearchBar></SearchBar>
                <FlexLayout className='navbar-right'>
                    <ShoppingCartButton />
                    <User />
                    <SvgIcon name='menu' width={23} height={23} />
                </FlexLayout>
            </div>
        </div>
    );
};

export default NavBar;
