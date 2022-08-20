import { css } from '@emotion/react';
import { useMemo } from 'react';

import MCFS from '@/views/common/MerchantSwiper/MerchantCardForSwiper';
import MerchantSwiper from '@/views/common/MerchantSwiper/MerchantSwiper';
import CategorySwiper from '@/views/common/CategorySwiper/CategorySwiper';

const MainContent = () => {
    const styles = useMemo(
        () => css`
            max-width: 1280px;
            margin: 0 auto;
        `,
        []
    );

    const arr = new Array(20).fill(MCFS, 0, 20);

    return (
        <>
            <div css={styles}>
                <MerchantSwiper title='LALALA' showSlides={2}>
                    {arr.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </MerchantSwiper>
                <MerchantSwiper title='LALALA' showSlides={2}>
                    {arr.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </MerchantSwiper>
                <MerchantSwiper title='LALALA' showSlides={2}>
                    {arr.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </MerchantSwiper>
                <CategorySwiper showSlides={5}>
                    {arr.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </CategorySwiper>
            </div>
        </>
    );
};
export default MainContent;
