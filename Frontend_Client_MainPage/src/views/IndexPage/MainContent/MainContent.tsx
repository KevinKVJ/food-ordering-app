import { css } from '@emotion/react';
import { useEffect, useMemo } from 'react';

import image2 from '@/assets/mockPages/image2.jpg';
import CategorySwiper from '@/views/common/CategorySwiper/CategorySwiper';
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

    const imgWrapperStyle = css`
        text-align: center;
        padding: 0 2px;
    `;
    const imgStyle = useMemo(
        () => css`
            width: 100%;
            height: 50px;
            border-radius: 4px;

            object-fit: cover;
            object-position: center;
        `,
        []
    );

    const arr1 = new Array(20).fill(MCFS, 0, 20);
    const arr2 = new Array(40).fill(
        () => <img src={image2} alt='' css={imgStyle} />,
        0,
        40
    );

    // useEffect(() => {
    //     console.log(arr2);
    // }, [arr2]);

    return (
        <>
            <div css={styles}>
                <CategorySwiper showSlides={10}>
                    {arr2.map((Item, key) => {
                        return (
                            <div key={key} css={imgWrapperStyle}>
                                {key + 1}
                                <Item imgHeight={200} />
                            </div>
                        );
                    })}
                </CategorySwiper>
                <MerchantSwiper title='LALALA' showSlides={2}>
                    {arr1.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </MerchantSwiper>
                <MerchantSwiper title='LALALA' showSlides={2}>
                    {arr1.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </MerchantSwiper>
                <MerchantSwiper title='LALALA' showSlides={2}>
                    {arr1.map((Item: typeof MCFS, key) => (
                        <Item key={key} imgHeight={200} />
                    ))}
                </MerchantSwiper>
            </div>
        </>
    );
};
export default MainContent;
