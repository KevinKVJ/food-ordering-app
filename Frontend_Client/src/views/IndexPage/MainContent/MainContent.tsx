import { css } from '@emotion/react';
import { useMemo } from 'react';

import MCFS from '@/views/common/MerchantSwiper/MerchantCardForSwiper';
import MerchantSwiper from '@/views/common/MerchantSwiper/MerchantSwiper';

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
            </div>
        </>
    );
};
export default MainContent;
