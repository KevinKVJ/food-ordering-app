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
                <Swiper>
                    <div>
                        <h3>1</h3>
                    </div>
                    <div>
                        <h3>2</h3>
                    </div>
                    <div>
                        <h3>3</h3>
                    </div>
                    <div>
                        <h3>4</h3>
                    </div>
                    <div>
                        <h3>5</h3>
                    </div>
                    <div>
                        <h3>6</h3>
                    </div>
                </Swiper>
            </div>
        </>
    );
};
export default MainContent;
