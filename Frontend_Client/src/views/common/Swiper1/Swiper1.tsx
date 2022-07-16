import Swiper from '@/components/Swiper/Swiper';
import type { Settings } from 'react-slick';
import { ElementRef, PropsWithChildren, useCallback, useEffect, useMemo, useRef, useState, Children } from 'react';
import { css } from '@emotion/react';
import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import lodash from 'lodash';

interface swiperProps extends PropsWithChildren {
    title?: string;
    showSlides?: number;
    showSpeed?: number;
}

const Swiper1 = ({ title, children, showSlides = 6, showSpeed = 500 }: swiperProps) => {
    const [prevValid, setPrevValid] = useState(true);
    const [nextValid, setNextValid] = useState(true);
    const [currentSlide, setCurrentSlide] = useState(0);
    const setting: Settings = {
        initialSlide: currentSlide,
        slidesToShow: showSlides,
        slidesToScroll: showSlides,
        infinite: false,
        dots: false,
        speed: 500,
        swipe: false,
        // centerMode: true,
    };

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
            user-select: none;
            /* } */
        `,
        []
    );
    const swiper1_wrapper = useMemo(() => css``, []);
    const swiper1_title_button = useMemo(() => css``, []);

    const swiper_next = useMemo(() => css``, []);

    const childrenLength = useMemo(() => Children.toArray(children).length, [children]);

    useEffect(() => {
        // console.log(Children.toArray(children).length);
        console.log('父组件init');
    }, []);

    useEffect(() => {
        console.log('父组件currentSlide变化');
        if (currentSlide - showSlides < 0) setPrevValid(false);
        else setPrevValid(true);
        if (currentSlide + showSlides > childrenLength - 1) setNextValid(false);
        else setNextValid(true);
    }, [currentSlide]);

    const SlideChange = useCallback(
        lodash.debounce(setCurrentSlide, showSpeed + 10, {
            leading: true,
            trailing: false,
        }),
        [showSpeed]
    );

    return (
        <div css={swiper1_wrapper}>
            <div className='swiper1_title_button' css={swiper1_title_button}>
                <FlexLayout justifyContent='space-between'>
                    <h2>{title}</h2>
                    <FlexLayout className='swiper1_buttons' spacing='small'>
                        <div
                            className='swiper1_prev'
                            css={swiper1_button}
                            onClick={() => SlideChange(currentSlide - showSlides)}>
                            <SvgIcon name='previous' width={15} height={15}></SvgIcon>
                        </div>
                        <div
                            className='swiper1_next'
                            css={swiper1_button}
                            onClick={() => SlideChange(currentSlide + showSlides)}>
                            <SvgIcon name='next' width={15} height={15}></SvgIcon>
                        </div>
                    </FlexLayout>
                </FlexLayout>
            </div>
            <Swiper currentSlide={currentSlide} {...setting}>
                {children}
            </Swiper>
        </div>
    );
};

export default Swiper1;
