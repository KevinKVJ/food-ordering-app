import { css } from '@emotion/react';
import { Children, FC, PropsWithChildren, useMemo, useRef, useState } from 'react';
import type { Settings } from 'react-slick';

import SVGIcon from '@/components/SvgIcon';
import Swiper from '@/components/Swiper/Swiper';
import { SwiperRefTypes } from '@/components/Swiper/SwiperType';

interface ICSwiperProps extends PropsWithChildren {
    showSlides?: number;
    initialSlide?: number;
}

const CategorySwiper: FC<ICSwiperProps> = ({
    showSlides = 10,
    initialSlide = 0,
    children,
}) => {
    const [prevValid, setPrevValid] = useState(true);
    const [nextValid, setNextValid] = useState(true);
    const [currentSlide, setCurrentSlide] = useState(initialSlide);
    const [currentPage, setCurrentPage] = useState(
        Math.floor(initialSlide / (showSlides as number) + 1)
    );
    const swiper1Ref = useRef<SwiperRefTypes>(null);

    const swiperButton = useMemo(
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

    const swiperButtonBanned = css`
        background-color: rgb(247, 247, 247);
        cursor: not-allowed;
        > svg {
            fill: rgb(178, 178, 178);
        }
    `;

    const csWrapper = css`
        position: relative;
        .cs-button {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            z-index: 500;
        }
        .icon-left {
            left: 0;
        }
        .icon-right {
            right: 0;
        }
    `;

    const childrenLength = useMemo(() => Children.toArray(children).length, [children]);

    const setting: Settings = {
        // initialSlide: 0,
        slidesToShow: showSlides,
        slidesToScroll: showSlides,
        infinite: false,
        dots: false,
        speed: 500,
        swipe: false,
        // centerMode: true,
    };

    return (
        <div className='cate-swiper-wrapper' css={csWrapper}>
            <div
                className='cs-button icon-left'
                css={[swiperButton, prevValid ? null : swiperButtonBanned]}
            >
                <SVGIcon name='previous' width={15} height={15} />
            </div>
            <div
                className='cs-button icon-right'
                css={[swiperButton, nextValid ? null : swiperButtonBanned]}
            >
                <SVGIcon name='next' width={15} height={15} />
            </div>
            <Swiper
                ref={swiper1Ref}
                {...setting}
                setCurrentPage={(pageVal: number) => setCurrentPage(pageVal)}
                setCurrentSlide={(slideVal: number) => setCurrentSlide(slideVal)}
            >
                {children}
            </Swiper>
        </div>
    );
};

export default CategorySwiper;
