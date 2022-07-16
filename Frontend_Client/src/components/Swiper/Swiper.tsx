import { Component, forwardRef, PropsWithChildren, useEffect, useImperativeHandle, useMemo, useRef } from 'react';
import Slider from 'react-slick';
import type { Settings } from 'react-slick';
import 'slick-carousel/slick/slick-theme.scss';
import 'slick-carousel/slick/slick.scss';

/* Really appreciate the react-slick dev group */
interface SwiperProps extends PropsWithChildren<Settings> {
    currentSlide: number;
}

const Swiper = ({ currentSlide, children, ...swiperSettings }: SwiperProps) => {
    const swiper_ref = useRef<Slider>(null);

    useEffect(() => {
        console.log("子组件执行");
        swiper_ref.current?.slickGoTo(currentSlide);
        return () => {
            console.log("lalala");
        }
    }, [currentSlide]);

    const settings = useMemo<Settings>(
        () => ({
            speed: 500,
            afterChange: curr => {
                console.log(`Current Slide`, Math.ceil(curr / 3));
                console.log(`CurrentIndex`, curr);
            },
            ...swiperSettings,
            // infinite: true,
            // slidesToShow: 3,
            // slidesToScroll: 3,
        }),
        [swiperSettings]
    );
    return (
        <div>
            <Slider {...settings} ref={swiper_ref}>
                {children}
            </Slider>
        </div>
    );
};

export default Swiper;
