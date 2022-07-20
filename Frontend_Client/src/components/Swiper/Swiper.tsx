import 'slick-carousel/slick/slick-theme.scss';
import 'slick-carousel/slick/slick.scss';

import { PropsWithChildren, useEffect, useMemo, useRef } from 'react';
import type { Settings } from 'react-slick';
/* Component, forwardRef, useImperativeHandle */
import Slider from 'react-slick';

/* Really appreciate the react-slick dev group */
interface SwiperProps extends PropsWithChildren<Settings> {
    currentSlide: number;
}

const Swiper = ({ currentSlide, children, ...swiperSettings }: SwiperProps) => {
    const swiperRef = useRef<Slider>(null);

    useEffect(() => {
        console.log('子组件执行');
        swiperRef.current?.slickGoTo(currentSlide);
        return () => {
            console.log('lalala');
        };
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
            <Slider {...settings} ref={swiperRef}>
                {children}
            </Slider>
        </div>
    );
};

export default Swiper;
