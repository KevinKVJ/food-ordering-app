import { css } from '@emotion/react';
import { useMemo } from 'react';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import Swiper1 from '@/views/common/Swiper1/Swiper1';
import image2 from '@/assets/mockPages/image2.jpg';

const MainContent = () => {
    const styles = useMemo(() => css``, []);
    const img_style = useMemo(
        () => css`
            width: 100%;
            height: 170px;
            object-fit: cover;
            object-position: center;
        `,
        []
    );
    return (
        <>
            <div css={styles}>
                <Swiper1 title='LALALA'>
                    <div>
                        0
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        1
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        2
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        3
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        4
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        5
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        6
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        7
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        8
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        9
                        <img src={image2} alt='' css={img_style} />
                    </div>
                    <div>
                        10
                        <img src={image2} alt='' css={img_style} />
                    </div>
                </Swiper1>
            </div>
        </>
    );
};
export default MainContent;
