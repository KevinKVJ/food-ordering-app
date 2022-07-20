import { css } from '@emotion/react';
import lodash from 'lodash';
import { Children, PropsWithChildren, useCallback, useEffect, useMemo, useState } from 'react';
import type { Settings } from 'react-slick';

import FlexLayout from '@/components/FlexLayout/FlexLayout';
import SvgIcon from '@/components/SvgIcon';
import Swiper from '@/components/Swiper/Swiper';

interface swiperProps extends PropsWithChildren {
    title?: string;
    showSlides?: number;
    showSpeed?: number;
}

const Swiper1 = ({ title, children, showSlides = 3, showSpeed = 500, ...props }: swiperProps) => {
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
        ...props,
        // centerMode: true,
    };

    const swiper1Button = useMemo(
        () => css`
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
        `,
        []
    );
    const swiper1Wrapper = useMemo(() => css``, []);
    const swiper1TitleButton = useMemo(
        () => css`
            padding: 0 20px;
            /* border-bottom: 2px solid #e9e9ea; */
        `,
        []
    );

    const swiperButtonPrev = useMemo(
        () =>
            !prevValid
                ? css`
                      background-color: rgb(247, 247, 247);
                      cursor: not-allowed;
                      > svg {
                          fill: rgb(178, 178, 178);
                      }
                  `
                : css``,
        [prevValid]
    );
    const swiperButtonNext = useMemo(
        () =>
            !nextValid
                ? css`
                      background-color: rgb(247, 247, 247);
                      > svg {
                          fill: rgb(178, 178, 178);
                      }
                  `
                : css``,
        [nextValid]
    );

    const childrenLength = useMemo(() => Children.toArray(children).length, [children]);

    useEffect(() => {
        // console.log(Children.toArray(children).length);
        console.log('父组件init');
    }, []);

    useEffect(() => {
        console.log('父组件currentSlide变化');
        currentSlide - showSlides < 0 ? setPrevValid(false) : setPrevValid(true);
        currentSlide + showSlides > childrenLength - 1 ? setNextValid(false) : setNextValid(true);
    }, [currentSlide]);

    const slideChange = useCallback(
        lodash.debounce(
            (setValue: typeof currentSlide) => {
                setValue >= 0 && setValue <= childrenLength - 1 && setCurrentSlide(setValue);
            },
            showSpeed + 10,
            {
                leading: true,
                trailing: false,
            }
        ),
        [showSpeed]
    );

    return (
        <div css={swiper1Wrapper}>
            <div className='swiper1_title_button' css={swiper1TitleButton}>
                <FlexLayout justifyContent='space-between'>
                    <h2>{title}</h2>
                    <FlexLayout className='swiper1_buttons' spacing='small'>
                        <div
                            className='swiper1_prev'
                            css={[swiper1Button, swiperButtonPrev]}
                            onClick={() => slideChange(currentSlide - showSlides)}>
                            <SvgIcon name='previous' width={15} height={15}></SvgIcon>
                        </div>
                        <div
                            className='swiper1_next'
                            css={[swiper1Button, swiperButtonNext]}
                            onClick={() => slideChange(currentSlide + showSlides)}>
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
