import Swiper from '@/components/Swiper/Swiper';
import type { Settings } from 'react-slick';
import { ElementRef, PropsWithChildren, useCallback, useMemo, useRef, useState } from 'react';
import { css } from '@emotion/react';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import Slider from 'react-slick';

interface swiperProps extends PropsWithChildren {
    title?: string;
}

const Swiper1 = ({ title, children }: swiperProps) => {
    const [currentSlide, setCurrentSlide] = useState(0);
    // const swiper1_ref = useRef<ElementRef<typeof Swiper>>(null);
    const setting: Settings = {
        slidesToShow: 3,
        slidesToScroll: 3,
        infinite: false,
        dots:false,
    };

    const swiper_ref = useRef<ElementRef<typeof Swiper>>(null);

    const swiper1_button = useMemo(
        () => css`
            /* display: flex;
            gap: 0 20px;
            .swiper1_prev,.swiper1_next { */
            width: 34px;
            height: 34px;
            border: 1px solid #e9e9ea;
            border-radius: 1000px;

            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;

            cursor: pointer;
            user-select:none;
            /* } */
        `,
        []
    );
    const swiper1_wrapper = useMemo(() => css``, []);
    const swiper1_title_button = useMemo(() => css``, []);

    const swiper_next = useMemo(() => css``, []);
    return (
        <div css={swiper1_wrapper}>
            <div className='swiper1_title_button' css={swiper1_title_button}>
                <FlexLayout justifyContent='space-between'>
                    <h2>{title}</h2>
                    <FlexLayout className='swiper1_buttons' spacing='small'>
                        <div
                            className='swiper1_prev'
                            css={swiper1_button}
                            onClick={() => swiper_ref.current?.prevSlide()}>
                            <SvgIcon name='previous' width={15} height={15}></SvgIcon>
                        </div>
                        <div
                            className='swiper1_next'
                            css={swiper1_button}
                            onClick={() => swiper_ref.current?.nextSlide()}>
                            <SvgIcon name='next' width={15} height={15}></SvgIcon>
                        </div>
                    </FlexLayout>
                </FlexLayout>
            </div>
            <Swiper ref={swiper_ref} currentSlide={currentSlide} {...setting}>
                {children}
            </Swiper>
        </div>
    );
};

export default Swiper1;
