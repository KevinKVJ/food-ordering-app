import { css } from '@emotion/react';
import { useMemo } from 'react';

import image2 from '@/assets/mockPages/image2.jpg';
import Swiper1 from '@/views/common/Swiper1/Swiper1';

const MainContent = () => {
    const styles = useMemo(
        () => css`
            max-width: 1280px;
            margin: 0 auto;
        `,
        []
    );
    const imgStyle = useMemo(
        () => css`
            width: 100%;
            height: 170px;
            object-fit: cover;
            object-position: center;
        `,
        []
    );
    const arr = new Array(20).fill(<img src={image2} alt='' css={imgStyle} />, 0, 20);
    return (
        <>
            <div css={styles}>
                <Swiper1 title='LALALA'>
                    {arr.map((item, key) => (
                        <div key={key}>{item}</div>
                    ))}
                </Swiper1>
            </div>
        </>
    );
};
export default MainContent;
