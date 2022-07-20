import { css } from '@emotion/react';
import SvgIcon from '@/components/SvgIcon';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import { useMemo } from 'react';
import Logo from '@/views/common/Logo';
import DeliveryDropDown from './Delivery_Dropdown';
import AddressDType_DropDown from './Address_DeliveryType_Dropdown';
import ShoppingCart_Button from './ShoppingCart_Button';
import User from './User';
import SearchBar from './SearchBar';

export default () => {
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
                    <AddressDType_DropDown />
                </FlexLayout>
                <SearchBar></SearchBar>
                <FlexLayout className='navbar-right'>
                    <ShoppingCart_Button />
                    <User />
                    <SvgIcon name='menu' width={23} height={23} />
                </FlexLayout>
            </div>
        </div>
    );
};
