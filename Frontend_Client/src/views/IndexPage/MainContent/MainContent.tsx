import { css } from '@emotion/react';
import { useMemo } from 'react';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import Swiper from '@/components/Swiper/Swiper';

const MainContent = () => {
    const styles = useMemo(() => css``, []);
    return (
        <>
            <div css={styles}>
                MainContent
                <Swiper></Swiper>
            </div>
        </>
    );
};
export default MainContent;
